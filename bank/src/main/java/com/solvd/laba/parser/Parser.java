package com.solvd.laba.parser;

import com.solvd.laba.models.Client;
import com.solvd.laba.models.CreditSummary;
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
    public static final String PATH = "../bank/src/main/resources/xml/CreditSummary.xml";
    private final static Logger LOGGER = LogManager.getLogger(Parser.class);


    public static void main(String[] args) {
        try {
            print(Paths.get(PATH));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private static CreditSummary print(Path path)
            throws IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(Files.newInputStream(path.toFile().toPath()));
        CreditSummary c = new CreditSummary();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "Credit_Summary":
                        Attribute id = element.getAttributeByName(new QName("id"));
                        c.setId(Integer.parseInt(id.getValue()));
                        LOGGER.info("Credit_Summary id : {}", id.getValue());
                        break;
                    case "Salary":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Salary : {}", event.asCharacters().getData());
                            c.setSalary(Double.parseDouble(event.asCharacters().getData()));
                        }
                        break;
                    case "Patrimony":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Patrimony : {}", event.asCharacters().getData());
                            c.setPatrimony(Double.parseDouble(event.asCharacters().getData()));
                        }
                        break;
                    case "Credit_Taken":
                        event = reader.nextEvent();
                        if (event.isCharacters()) {
                            LOGGER.info("Credit_Taken : {}", event.asCharacters().getData());
                            c.setCreditTaken(Boolean.parseBoolean(event.asCharacters().getData()));
                        }
                        break;
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("Credit_Summary")) {
                    LOGGER.info("{}", "---");
                }
            }


        }
        return c;

    }

}

