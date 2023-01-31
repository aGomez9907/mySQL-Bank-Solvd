package com.solvd.laba.controller;

import com.solvd.laba.dao.IClientDAO;
import com.solvd.laba.models.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAO extends MySQLDAO implements IClientDAO {


    private static final Logger LOGGER = LogManager.getLogger();


    final String INSERT = "INSERT INTO bank_solvd.CLIENT (NAME, SURNAME, AGE, COUNTRY, ADDRESS, CREDIT_SUMMARY_ID, CERTIFICATE_DEPOSIT_ACCOUNT_ID, SAVING_ACCOUNT_ID, CHECKING_ACCOUNT_ID, OFFICE_ID, HOMEBANKING_ID) VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?)";
    final String UPDATE = "UPDATE bank_solvd.CLIENT SET NAME = ?, SURNAME = ?, AGE = ?, COUNTRY = ?, ADDRESS = ?, CREDIT_SUMMARY_ID = ? , CERTIFICATE_DEPOSIT_ACCOUNT_ID = ? , SAVING_ACCOUNT_ID = ? , CHECKING_ACCOUNT_ID = ? , OFFICE_ID = ? , HOMEBANKING_ID = ?  WHERE CLIENT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CLIENT WHERE CLIENT_ID = ?";
    final String SELECT_ONE = "SELECT NAME, SURNAME, AGE, COUNTRY, ADDRESS FROM bank_solvd.CLIENT WHERE CLIENT_ID = ?";
    final String SELECT_ALL = "SELECT NAME, SURNAME, AGE, COUNTRY, ADDRESS FROM bank_solvd.CLIENT";


    public MySQLClientDAO() throws SQLException {

    }


    @Override
    public Client insert(Client a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setString(1, a.getName());
            stat.setString(2, a.getSurname());
            stat.setInt(3, a.getAge());
            stat.setString(4, a.getCountry());
            stat.setString(5, a.getAddress());
            if (a.getCreditSummary().getId() != 0)
                stat.setInt(6, a.getCreditSummary().getId());
            else
                stat.setNull(6, java.sql.Types.NULL);
            if (a.getCertificateDepositAccount().getId() != 0)
                stat.setInt(7, a.getCertificateDepositAccount().getId());
            else
                stat.setNull(7, java.sql.Types.NULL);
            if (a.getSavingAccount().getId() != 0)
                stat.setInt(8, a.getSavingAccount().getId());
            else
                stat.setNull(8, java.sql.Types.NULL);
            if (a.getCheckingAccount().getId() != 0)
                stat.setInt(9, a.getCheckingAccount().getId());
            else
                stat.setNull(9, java.sql.Types.NULL);
            if (a.getOffice().getId() != 0)
                stat.setInt(10, a.getOffice().getId());
            else
                stat.setNull(10, java.sql.Types.NULL);
            if (a.getHomeBanking().getId() != 0)
                stat.setInt(11, a.getHomeBanking().getId());
            else
                stat.setNull(11, java.sql.Types.NULL);
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }
            LOGGER.info("Account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return a;
    }

    @Override
    public void update(Client a) {
        LOGGER.info("Updating Client with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(12, a.getId());
            stat.setString(1, a.getName());
            stat.setString(2, a.getSurname());
            stat.setInt(3, a.getAge());
            stat.setString(4, a.getCountry());
            stat.setString(5, a.getAddress());
            if (a.getCreditSummary().getId() != 0)
                stat.setInt(6, a.getCreditSummary().getId());
            else
                stat.setNull(6, java.sql.Types.NULL);
            if (a.getCertificateDepositAccount().getId() != 0)
                stat.setInt(7, a.getCertificateDepositAccount().getId());
            else
                stat.setNull(7, java.sql.Types.NULL);
            if (a.getSavingAccount().getId() != 0)
                stat.setInt(8, a.getSavingAccount().getId());
            else
                stat.setNull(8, java.sql.Types.NULL);
            if (a.getCheckingAccount().getId() != 0)
                stat.setInt(9, a.getCheckingAccount().getId());
            else
                stat.setNull(9, java.sql.Types.NULL);
            if (a.getOffice().getId() != 0)
                stat.setInt(10, a.getOffice().getId());
            else
                stat.setNull(10, java.sql.Types.NULL);
            if (a.getHomeBanking().getId() != 0)
                stat.setInt(11, a.getHomeBanking().getId());
            else
                stat.setNull(11, java.sql.Types.NULL);
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public void delete(Client a) {
        LOGGER.info("Deleting Account with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Client selectOne(int id) {
        Client a = new Client();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                a.setId(id);
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
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
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
