package com.solvd.laba.mybatis.dao;

import com.solvd.laba.dao.ICheckingAccountDAO;
import com.solvd.laba.models.CheckingAccount;
import com.solvd.laba.mybatis.MyBatisDAOFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MyBatisCheckingAccountDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final SqlSessionFactory FACTORY = MyBatisDAOFactory.getSqlSessionFactory();

    public CheckingAccount getCheckingAccountByClientId(int id) {
        CheckingAccount checkingAccount = null;

        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            LOGGER.info("Getting checking account by client id");
            checkingAccount = checkingAccountDAO.getCheckingAccountByClientId(id);
        }
        return checkingAccount;
    }

    public CheckingAccount selectOne(int id) {
        CheckingAccount checkingAccount = null;

        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            LOGGER.info("Getting checking account");
            checkingAccount = checkingAccountDAO.selectOne(id);
        }
        return checkingAccount;
    }

    public CheckingAccount delete(CheckingAccount ca) throws SQLException {
        LOGGER.info("Deleting checking account");
        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            try {
                checkingAccountDAO.delete(ca);
                session.commit();
                LOGGER.info("Checking account deleted successfully");
            } catch (Exception e) {
                LOGGER.info("Error deleting checking account");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return ca;
    }

    public CheckingAccount insert(CheckingAccount ca) throws SQLException {
        LOGGER.info("Creating checking account");
        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            try {
                checkingAccountDAO.insert(ca);
                session.commit();
                LOGGER.info("Checking account added successfully");
            } catch (Exception e) {
                LOGGER.info("Error creating checking account");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return ca;
    }

    public CheckingAccount update(CheckingAccount ca) throws SQLException {
        LOGGER.info("Updating checking account");
        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            try {
                checkingAccountDAO.update(ca);
                session.commit();
                LOGGER.info("Checking account updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error updating checking account");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return ca;
    }

    public List<CheckingAccount> selectAll() {
        try (SqlSession session = FACTORY.openSession()) {
            ICheckingAccountDAO checkingAccountDAO = session.getMapper(ICheckingAccountDAO.class);
            return checkingAccountDAO.selectAll();
        }
    }
}
