package com.solvd.laba.patterns.factory;

import com.solvd.laba.dao.IDebitCardDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        DAOFactory factory = new DAOFactory();

        IDebitCardDAO jdbcDAO = factory.getDAO(DAOType.JDBC);
        LOGGER.info("Jdbc: " + jdbcDAO.selectOne(3).toString());


        IDebitCardDAO myBatisDAO = factory.getDAO(DAOType.MYBATIS);
        LOGGER.info("MyBatis: " + myBatisDAO.selectOne(3).toString());
    }
}
