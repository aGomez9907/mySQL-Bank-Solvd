package com.solvd.laba;

import com.solvd.laba.controller.*;
import com.solvd.laba.models.*;
import com.solvd.laba.service.CheckingAccountService;
import com.solvd.laba.service.ClientsService;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance().getConnection();

        //ClientsService cs = new ClientsService();
        CheckingAccountService cas = new CheckingAccountService();

        CheckingAccount ca = new CheckingAccount(10, 2500);
        CreditCard cc = new CreditCard(5000, 2000, "1/1/2031", 321, "Visa");
        DebitCard dc = new DebitCard("18/11/2030", 321, "Visa");
        cas.insert(ca, cc, dc);
        LOGGER.info(cas.selectOne(1).toString());
        MySQLCreditCardDAO ccdao = new MySQLCreditCardDAO();
        LOGGER.info(ccdao.getCreditCardByCheckingAccountId(1)); //card not associating with account!! need help
        LOGGER.info(ccdao.getById(1));


        ConnectionPool.getInstance().close();


    }
}
