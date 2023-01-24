package com.solvd.laba.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Validate {

    private final static Logger LOGGER = LogManager.getLogger(Validate.class);

    public static void main(String[] args) {
        try {
            String xsd = "./src/main/resources/Client.xsd";
            String xml = "./src/main/resources/Client.xml";
            boolean isValid = validate(xsd, xml);
            LOGGER.info(String.format("XML file is %s", (isValid ? "valid." : "invalid.")));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static boolean validate(String xsd, String xml) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(Files.newInputStream(Paths.get(xml).toFile().toPath()));

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));

            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
        } catch (XMLStreamException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}