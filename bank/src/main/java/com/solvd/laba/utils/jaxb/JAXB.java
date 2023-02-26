package com.solvd.laba.utils.jaxb;

import com.solvd.laba.models.ATM;
import com.solvd.laba.models.BankBranchOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JAXB {
    public static final String OFFICE_PATH = "src/main/resources/xml/BankBranchOffice.xml";
    public static final String OFFICE_MARSHAL_PATH = "src/main/resources/xml/BankBranchOffice_MARSHAL.xml";
    private final static Logger LOGGER = LogManager.getLogger();

    public static BankBranchOffice unmarshall() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(BankBranchOffice.class);
        return (BankBranchOffice) context.createUnmarshaller()
                .unmarshal(new FileReader(OFFICE_PATH));
    }

    public static void main(String[] args) throws JAXBException, IOException {
        BankBranchOffice office = new BankBranchOffice(1, 2000, "Salvo st. 123", "Argentina");
        ATM atm1 = new ATM(1, 5000, office);
        ATM atm2 = new ATM(2, 5000, office);
        ATM atm3 = new ATM(3, 5000, office);
        ArrayList<ATM> atms = new ArrayList<>();
        atms.add(atm1);
        atms.add(atm2);
        atms.add(atm3);
        office.setAtms(atms);

        try {
            File file2 = new File(OFFICE_MARSHAL_PATH);

            JAXBContext jaxbContext = JAXBContext.newInstance(BankBranchOffice.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(office, file2);

            Unmarshaller jaxbUnmarshal = jaxbContext.createUnmarshaller();
            BankBranchOffice office1 = (BankBranchOffice) jaxbUnmarshal.unmarshal(Files.newInputStream(Paths.get(OFFICE_PATH)));
            LOGGER.info("\n");
            LOGGER.info(office1.toString());


        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}
