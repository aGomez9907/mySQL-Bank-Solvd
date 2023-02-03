package com.solvd.laba;

import com.solvd.laba.models.DebitCard;
import com.solvd.laba.service.MyBatisDebitCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisTask {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        MyBatisDebitCardService debitService = new MyBatisDebitCardService();
        LOGGER.info(debitService.selectOne(3));
        DebitCard dc = new DebitCard(3, "10/10/2023", 123, "Visa");
        LOGGER.info(debitService.insert(dc));
    }
}
