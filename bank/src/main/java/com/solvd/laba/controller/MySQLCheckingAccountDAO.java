package com.solvd.laba.controller;

import com.solvd.laba.dao.ICheckingAccountDAO;
import com.solvd.laba.models.CheckingAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCheckingAccountDAO extends MySQLDAO implements ICheckingAccountDAO {

    private final static Logger LOGGER = LogManager.getLogger();
    final String INSERT = "INSERT INTO bank_solvd.CHECKING_ACCOUNT (CHECKS, BALANCE, CREDIT_CARD_ID, DEBIT_CARD_ID) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.CHECKING_ACCOUNT SET CHECKS = ?, BALANCE = ?, CREDIT_CARD_ID = ?, DEBIT_CARD_ID = ? WHERE CHECKING_ACCOUNT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CHECKING_ACCOUNT WHERE CHECKING_ACCOUNT_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.CHECKING_ACCOUNT WHERE CHECKING_ACCOUNT_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.CHECKING_ACCOUNT";
    private final String CHECKING_BY_CLIENT_ID = "SELECT * FROM bank_solvd.CHECKING_ACCOUNT INNER JOIN bank_solvd.CLIENT ON CHECKING_ACCOUNT.CHECKING_ACCOUNT_ID = CLIENT.CHECKING_ACCOUNT_ID WHERE CLIENT_ID = ?";


    public MySQLCheckingAccountDAO() throws SQLException {

    }

    public CheckingAccount getCheckingAccountByClientId(int accountId) {
        CheckingAccount c = null;
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(CHECKING_BY_CLIENT_ID)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            c = new CheckingAccount();
            while (rs.next()) {
                c.setId(rs.getInt("CHECKING_ACCOUNT_ID"));
                c.setChecks(rs.getInt("CHECKS"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(CheckingAccount a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setInt(1, a.getChecks());
            stat.setDouble(2, a.getBalance());
            if (a.getCreditCard().getId() != 0)
                stat.setInt(3, a.getCreditCard().getId());
            else
                stat.setNull(3, java.sql.Types.NULL);
            if (a.getDebitCard().getId() != 0)
                stat.setInt(4, a.getDebitCard().getId());
            else
                stat.setNull(4, java.sql.Types.NULL);
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }

            LOGGER.info("Checking account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CheckingAccount a) {
        LOGGER.info("Updating checking account with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(5, a.getId());
            stat.setInt(1, a.getChecks());
            stat.setDouble(2, a.getBalance());
            if (a.getDebitCard().getId() != 0)
                stat.setInt(3, a.getDebitCard().getId());
            else stat.setInt(3, java.sql.Types.NULL);
            if (a.getCreditCard().getId() != 0)
                stat.setInt(4, a.getCreditCard().getId());
            else stat.setInt(4, java.sql.Types.NULL);
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CheckingAccount a) {
        LOGGER.info("Deleting checking account with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public CheckingAccount selectOne(int id) {
        CheckingAccount c = new CheckingAccount();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("CHECKING_ACCOUNT_ID"));
                c.setChecks(rs.getInt("CHECKS"));
                c.setBalance(rs.getDouble("BALANCE"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<CheckingAccount> selectAll() {
        ArrayList<CheckingAccount> ca = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ca.add(new CheckingAccount(rs.getInt("CHECKING_ACCOUNT_ID"), rs.getInt("CHECKS"), rs.getDouble("BALANCE")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return ca;
    }
}
