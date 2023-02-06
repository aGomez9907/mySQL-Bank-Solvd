package com.solvd.laba.mybatis.dao;


import com.solvd.laba.dao.IDebitCardDAO;
import com.solvd.laba.models.DebitCard;
import com.solvd.laba.mybatis.MyBatisDAOFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class MyBatisDebitCardDAO implements IDebitCardDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private static final SqlSessionFactory FACTORY = MyBatisDAOFactory.getSqlSessionFactory();

    public DebitCard getDebitCardByCheckingAccountId(int id) {
        DebitCard debitCard = null;

        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            LOGGER.info("Getting debit card by checking account id");
            debitCard = debitCardDAO.getDebitCardByCheckingAccountId(id);
        }
        return debitCard;
    }

    public DebitCard selectOne(int id) {
        DebitCard debitCard = null;

        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            LOGGER.info("Getting debit card");
            debitCard = debitCardDAO.selectOne(id);
        }
        return debitCard;
    }

    public DebitCard delete(DebitCard dc) {
        LOGGER.info("Deleting debit card");
        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            try {
                debitCardDAO.delete(dc);
                session.commit();
                LOGGER.info("Debit card deleted successfully");
            } catch (Exception e) {
                LOGGER.info("Error deleting debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return dc;
    }

    public DebitCard insert(DebitCard dc) {
        LOGGER.info("Creating debit card");
        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            try {
                debitCardDAO.insert(dc);
                session.commit();
                LOGGER.info("Debit card added successfully");
            } catch (Exception e) {
                LOGGER.info("Error creating debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return dc;
    }

    public DebitCard update(DebitCard dc) {
        LOGGER.info("Updating debit card");
        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            try {
                debitCardDAO.update(dc);
                session.commit();
                LOGGER.info("Debit card updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error updating debit card");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return dc;
    }

    public List<DebitCard> selectAll() {
        try (SqlSession session = FACTORY.openSession()) {
            IDebitCardDAO debitCardDAO = session.getMapper(IDebitCardDAO.class);
            List<DebitCard> debitCards = debitCardDAO.selectAll();
            return debitCards;
        }
    }
}
