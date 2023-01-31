package com.solvd.laba;

import com.solvd.laba.models.*;
import com.solvd.laba.service.ClientsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {

        ClientsService cs = new ClientsService();


        //CHECKING ACCOUNT
        CheckingAccount ca = new CheckingAccount(10, 2500);
        CreditCard cc = new CreditCard(5000, 2000, "1/1/2031", 321, "Visa");
        DebitCard dc = new DebitCard("18/11/2030", 321, "Visa");

        ca.setDebitCard(dc);
        ca.setCreditCard(cc);


        //OFFICE
        BankBranchOffice office = new BankBranchOffice(10000, "N street 123", "Chile");

        ATM atm1 = new ATM(5000);
        ATM atm2 = new ATM(10000);

        ArrayList<ATM> atms = new ArrayList<>();
        atms.add(atm1);
        atms.add(atm2);


        office.setAtms(atms);


        //CREDIT SUMMARY
        CreditSummary creditSummary = new CreditSummary(20000, 2000, true);

        //CERTIFICATE DEPOSIT ACCOUNT
        CertificateDepositAccount certificateDepositAccount = new CertificateDepositAccount("11/12/2022", "11/12/2023", 0.5, 80000);

        //SAVING ACCOUNT
        SavingAccount savingAccount = new SavingAccount(4, 200);

        //HOMEBANKING
        HomeBanking homeBanking = new HomeBanking("AlejoG", "Sape123");


        //CLIENT
        Client client = new Client("Alejo", "Gomez", 23, "Argentina", "fk St. 123");
        client.setCheckingAccount(ca);
        client.setOffice(office);
        client.setCreditSummary(creditSummary);
        client.setCertificateDepositAccount(certificateDepositAccount);
        client.setSavingAccount(savingAccount);
        client.setHomeBanking(homeBanking);


        //Return client from insertion to get the same client but with the assigned id.

        client = cs.insert(client);
        //


        LOGGER.info(cs.selectOne(client.getId()).toString());


        //UPDATE

        creditSummary.setCreditTaken(false);
        creditSummary.setSalary(50000);
        client.setCreditSummary(creditSummary);

        cs.update(client);

        LOGGER.info(cs.selectOne(client.getId()).toString());


    }
}
