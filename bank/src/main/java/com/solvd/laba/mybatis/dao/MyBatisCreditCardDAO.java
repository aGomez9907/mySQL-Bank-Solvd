package com.solvd.laba.mybatis.dao;

import com.solvd.laba.dao.ICreditCardDAO;
import com.solvd.laba.models.CreditCard;
import com.solvd.laba.mybatis.MyBatisDAOFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MyBatisCreditCardDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final SqlSessionFactory FACTORY = MyBatisDAOFactory.getSqlSessionFactory();

    public CreditCard getCreditCardByCheckingAccountId(int id) {
        CreditCard creditCard = null;

        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            LOGGER.info("Getting debit card by checking account id");
            creditCard = creditCardDAO.getCreditCardByCheckingAccountId(id);
        }
        return creditCard;
    }


    public CreditCard selectOne(int id) {
        CreditCard creditCard = null;

        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            LOGGER.info("Getting debit card");
            creditCard = creditCardDAO.selectOne(id);
        }
        return creditCard;
    }

    public CreditCard delete(CreditCard cc) throws SQLException {
        LOGGER.info("Deleting debit card");
        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            try {
                creditCardDAO.delete(cc);
                session.commit();
                LOGGER.info("Debit card deleted successfully");
            } catch (Exception e) {
                LOGGER.info("Error deleting debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return cc;
    }

    public CreditCard insert(CreditCard cc) throws SQLException {
        LOGGER.info("Creating debit card");
        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            try {
                creditCardDAO.insert(cc);
                session.commit();
                LOGGER.info("Debit card added successfully");
            } catch (Exception e) {
                LOGGER.info("Error creating debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return cc;
    }

    public CreditCard update(CreditCard cc) throws SQLException {
        LOGGER.info("Updating debit card");
        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            try {
                creditCardDAO.update(cc);
                session.commit();
                LOGGER.info("Debit card updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error updating debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return cc;
    }

    public List<CreditCard> selectAll() {
        try (SqlSession session = FACTORY.openSession()) {
            ICreditCardDAO creditCardDAO = session.getMapper(ICreditCardDAO.class);
            List<CreditCard> creditCards = creditCardDAO.selectAll();
            return creditCards;
        }
    }
}
