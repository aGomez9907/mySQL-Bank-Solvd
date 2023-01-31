package com.solvd.laba.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.laba.models.ATM;
import com.solvd.laba.models.BankBranchOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH = "src/main/resources/json/Office.json";


    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        BankBranchOffice office = new BankBranchOffice(1, 50000, "St st. 123", "Argentina");

        ATM atm1 = new ATM(1, 5000, office);
        ATM atm2 = new ATM(2, 5000, office);
        ATM atm3 = new ATM(3, 5000, office);
        ArrayList<ATM> atms = new ArrayList<>();
        atms.add(atm1);
        atms.add(atm2);
        atms.add(atm3);
        office.setAtms(atms);


        marshall(mapper, office, PATH);
        unmarshall(mapper, BankBranchOffice.class, PATH);

    }

    static void marshall(ObjectMapper objMap, Object obj, String path) {
        try {
            objMap.enable(SerializationFeature.INDENT_OUTPUT);
            objMap.writeValue(new File(path), obj);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    static Object unmarshall(ObjectMapper objMap, Class cl, String path) {
        Object obj;
        try {
            obj = objMap.readValue(new File(path), cl);
            return obj;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


}
