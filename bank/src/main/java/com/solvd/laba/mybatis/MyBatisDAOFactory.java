package com.solvd.laba.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class MyBatisDAOFactory {

    private final static Logger log = LogManager.getLogger(MyBatisDAOFactory.class);
    private final static MyBatisDAOFactory myBatisDaoFactory = new MyBatisDAOFactory();
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisDAOFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis_cfg.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            log.error("Exception while reading configuration", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}