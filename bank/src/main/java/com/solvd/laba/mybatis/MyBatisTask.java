package com.solvd.laba.mybatis;

import com.solvd.laba.models.DebitCard;
import com.solvd.laba.mybatis.dao.MyBatisDebitCardDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisTask {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        MyBatisDebitCardDAO debitDAO = new MyBatisDebitCardDAO();

        //DebitCard dc = new DebitCard("10/10/2023", 123, "Visa");
        //DebitCard dc1 = debitDAO.insert(dc);
        //LOGGER.info(dc1.toString());

//        dc1.setProvider("Mastercard");
//        debitDAO.update(dc1);
//        LOGGER.info(debitDAO.selectOne(dc1.getId()));
        //debitDAO.selectAll();
        //debitDAO.delete(dc1);

        debitDAO.selectOne(3);

    }
}
