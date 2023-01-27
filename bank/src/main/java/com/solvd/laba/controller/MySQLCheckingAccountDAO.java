package com.solvd.laba.controller;

import com.solvd.laba.dao.ICheckingAccountDAO;
import com.solvd.laba.models.CertificateDepositAccount;
import com.solvd.laba.models.CheckingAccount;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCheckingAccountDAO extends MySQLDAO implements ICheckingAccountDAO {

    private final static Logger LOGGER = LogManager.getLogger();
    final String INSERT1 = "INSERT INTO bank_solvd.CHECKING_ACCOUNT (CHECKS, BALANCE, CREDIT_CARD_ID, DEBIT_CARD_ID) VALUES (?, ?, ?, ?)";
    final String INSERT = "INSERT INTO bank_solvd.CHECKING_ACCOUNT (CHECKS, BALANCE) VALUES (?, ?)";

    final String UPDATE = "UPDATE bank_solvd.CHECKING_ACCOUNT SET CHECKS = ?, BALANCE = ? WHERE CHECKING_ACCOUNT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CHECKING_ACCOUNT WHERE CHECKING_ACCOUNT_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.CHECKING_ACCOUNT WHERE CHECKING_ACCOUNT_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.CHECKING_ACCOUNT";
    private final String CHECKING_BY_CLIENT_ID = "SELECT bank_solvd.CHECKING_ACCOUNT.* FROM CHECKING_ACCOUNT INNER JOIN bank_solvd.CLIENT ON CHECKING_ACCOUNT.CHECKING_ACCOUNT_ID = CLIENT.CHECKING_ACCOUNT_ID WHERE CLIENT_ID = ?";


    public MySQLCheckingAccountDAO() throws SQLException {

    }

    public CheckingAccount getCheckingAccountByClientId(int accountId) {
        CheckingAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(CHECKING_BY_CLIENT_ID);
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
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getChecks());
            stat.setDouble(2, a.getBalance());
//            stat.setInt(3, a.getCreditCard().getId());
//            stat.setInt(4, a.getDebitCard().getId());
            stat.executeUpdate();
            LOGGER.info("Checking account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CheckingAccount a) {
        LOGGER.info("Updating checking account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(1, a.getChecks());
            stat.setDouble(2, a.getBalance());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CheckingAccount a) {
        LOGGER.info("Deleting checking account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public CheckingAccount selectOne(int id) {
        CheckingAccount c = new CheckingAccount();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
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
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
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
