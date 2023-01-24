package com.solvd.laba.controller;

import com.solvd.laba.dao.IClientDAO;
import com.solvd.laba.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLClientDAO extends MySQLDAO implements IClientDAO {

    private final static String CLIENT_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.CLIENT.* FROM CLIENT INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON CLIENT.CLIENT_ID = ACCOUNTS_MAIN_TABLE.CLIENT_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";
    private Connection conn;


    public Client getClientByAccountsMainId(int accountId) {
        Client c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    CLIENT_BY_ACCOUNTS_MAIN_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            c = new Client();
            c.setId(resultSet.getInt("CLIENT_ID"));
            c.setName(resultSet.getString("NAME"));
            c.setSurname(resultSet.getString("SURNAME"));
            c.setAge(resultSet.getInt("AGE"));
            c.setCountry(resultSet.getString("COUNTRY"));
            c.setAddress(resultSet.getString("ADDRESS"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(Client client) {
        //not finished
    }

    @Override
    public void update(Client client) {
        //not finished
    }

    @Override
    public void delete(Client client) {
        //not finished
    }

    @Override
    public Client selectOne(int id) {
        //not finished
        return null;
    }

    @Override
    public List<Client> selectAll() {
        //not finished
        return null;
    }
}
