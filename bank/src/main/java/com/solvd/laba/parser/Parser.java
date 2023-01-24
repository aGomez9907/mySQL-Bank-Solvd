package com.solvd.laba.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
    public static final String PATH = "../bank/src/main/resources/Client.xml";
    private final static Logger LOGGER = LogManager.getLogger(Parser.class);


    public static void main(String[] args) {
        try {
            print(Paths.get(PATH));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void print(Path path)
            throws IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(Files.newInputStream(path.toFile().toPath()));
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "client":
                        Attribute id = element.getAttributeByName(new QName("id"));
                        LOGGER.info("Staff id : {}", id.getValue());
                        break;
                    case "name":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Name : {}", event.asCharacters().getData());
                        }
                        break;
                    case "surname":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Surame : {}", event.asCharacters().getData());
                        }
                        break;
                    case "age":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Age : {}", event.asCharacters().getData());
                        }
                        break;
                    case "country":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("country : {}", event.asCharacters().getData());
                        }
                        break;
                    case "address":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("address : {}", event.asCharacters().getData());
                        }
                        break;
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("client")) {
                    LOGGER.info("{}", "---");
                }
            }

        }

    }

}

