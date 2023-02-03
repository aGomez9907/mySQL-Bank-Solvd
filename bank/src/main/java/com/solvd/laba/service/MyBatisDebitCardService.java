package com.solvd.laba.service;

import com.solvd.laba.controller.MySQLDebitCardDAO;
import com.solvd.laba.models.DebitCard;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class MyBatisDebitCardService {
    private final static Logger LOGGER = LogManager.getLogger();
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis_cfg.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public DebitCard selectOne(int id) {
        DebitCard debitCard = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MySQLDebitCardDAO debitCardDAO = session.getMapper(MySQLDebitCardDAO.class);
            debitCard = debitCardDAO.selectOne(id);
            LOGGER.info("Getting debit card");
        }
        return debitCard;
    }


    public DebitCard insert(DebitCard dc) throws SQLException {
        LOGGER.info("Creating debit card");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            MySQLDebitCardDAO debitCardDAO = session.getMapper(MySQLDebitCardDAO.class);
            try {
                debitCardDAO.insert(dc);
                session.commit();
                LOGGER.info("Customer added successfully");
            } catch (Exception e) {
                LOGGER.info("Error creating customer");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return dc;
    }
}
