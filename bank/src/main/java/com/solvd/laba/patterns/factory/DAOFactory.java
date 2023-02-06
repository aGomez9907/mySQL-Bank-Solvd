package com.solvd.laba.patterns.factory;

import com.solvd.laba.controller.MySQLDebitCardDAO;
import com.solvd.laba.dao.IDebitCardDAO;
import com.solvd.laba.mybatis.dao.MyBatisDebitCardDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOFactory {


    private final static Logger LOGGER = LogManager.getLogger();

    public IDebitCardDAO getDAO(DAOType daoType) {
        try {
            if (daoType == null) {
                LOGGER.info("No DAO type declared.");
            }
            switch (daoType) {
                case JDBC:
                    return new MySQLDebitCardDAO();
                case MYBATIS:
                    return new MyBatisDebitCardDAO();
                default:
                    LOGGER.info("Incorrect DAO type.");
                    return null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

}


