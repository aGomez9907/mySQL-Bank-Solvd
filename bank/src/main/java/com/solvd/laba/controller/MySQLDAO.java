package com.solvd.laba.controller;

import com.solvd.laba.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLDAO {
    protected Connection conn;

    public MySQLDAO() throws SQLException {
        this.conn = ConnectionPool.getInstance().getConnection();
    }
}
