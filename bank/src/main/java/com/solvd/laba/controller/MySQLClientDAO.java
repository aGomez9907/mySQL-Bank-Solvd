package com.solvd.laba.controller;

import com.solvd.laba.dao.IClientDAO;
import com.solvd.laba.models.Client;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAO extends MySQLDAO implements IClientDAO {


    private static final Logger LOGGER = LogManager.getLogger();

    //private final static String CLIENT_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.CLIENT.* FROM CLIENT INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON CLIENT.CLIENT_ID = ACCOUNTS_MAIN_TABLE.CLIENT_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";

    final String INSERT = "INSERT INTO bank_solvd.CLIENT (NAME, SURNAME, AGE, COUNTRY, ADDRESS) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.CLIENT SET NAME = ?, SURNAME = ?, AGE = ?, COUNTRY = ?, ADDRESS = ? WHERE CLIENT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CLIENT WHERE CLIENT_ID = ?";
    final String SELECT_ONE = "SELECT NAME, SURNAME, AGE, COUNTRY, ADDRESS FROM bank_solvd.CLIENT WHERE CLIENT_ID = ?";
    final String SELECT_ALL = "SELECT NAME, SURNAME, AGE, COUNTRY, ADDRESS FROM bank_solvd.CLIENT";


    public MySQLClientDAO() throws SQLException {

    }

    @Override
    public void insert(Client a) {
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getName());
            stat.setString(2, a.getSurname());
            stat.setInt(3, a.getAge());
            stat.setString(4, a.getCountry());
            stat.setString(5, a.getAddress());
            stat.executeUpdate();
            LOGGER.info("Account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(Client a) {
        LOGGER.info("Updating Account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setString(1, a.getName());
            stat.setString(2, a.getSurname());
            stat.setInt(3, a.getAge());
            stat.setString(4, a.getCountry());
            stat.setString(5, a.getAddress());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void delete(Client a) {
        LOGGER.info("Deleting Account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Client selectOne(int id) {
        Client a = new Client();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                a.setId(rs.getInt("CLIENT_ID"));
                a.setName(rs.getString("NAME"));
                a.setSurname(rs.getString("SURNAME"));
                a.setAge(rs.getInt("AGE"));
                a.setCountry(rs.getString("COUNTRY"));
                a.setAddress(rs.getString("ADDRESS"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    @Override
    public List<Client> selectAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getInt("CLIENT_ID"), rs.getString("NAME"), rs.getString("SURNAME"), rs.getInt("AGE"), rs.getString("COUNTRY"), rs.getString("ADDRESS")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return clients;

    }


}
