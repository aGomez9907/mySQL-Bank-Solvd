package com.solvd.laba.controller;

import com.solvd.laba.dao.IAccountsMainDAO;
import com.solvd.laba.models.AccountsMain;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLAccountsMainDAO extends MySQLDAO implements IAccountsMainDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySQLAccountsMainDAO.class);
    final String INSERT = "INSERT INTO bank_solvd.ACCOUNTS_MAIN_TABLE (CLIENT_ID, CERTIFICATE_DEPOSIT_ACCOUNT_ID, SAVING_ACCOUNT_ID, CHECKING_ACCOUNT_ID, BALANCE, OFFICE_ID) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.ACCOUNTS_MAIN_TABLE SET CLIENT_ID = ?, CERTIFICATE_DEPOSIT_ACCOUNT_ID = ?, SAVING_ACCOUNT_ID = ?, CHECKING_ACCOUNT_ID = ?, BALANCE = ?, OFFICE_ID = ? WHERE ACCOUNT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.ACCOUNTS_MAIN_TABLE WHERE ACCOUNT_ID = ?";
    final String SELECT_ONE = "SELECT ACCOUNT_ID, CLIENT_ID, CERTIFICATE_DEPOSIT_ACCOUNT_ID, SAVING_ACCOUNT_ID, CHECKING_ACCOUNT_ID, BALANCE, OFFICE_ID FROM bank_solvd.ACCOUNTS_MAIN_TABLE WHERE ACCOUNT_ID = ?";
    final String SELECT_ALL = "SELECT ACCOUNT_ID, CLIENT_ID, CERTIFICATE_DEPOSIT_ACCOUNT_ID, SAVING_ACCOUNT_ID, CHECKING_ACCOUNT_ID, BALANCE, OFFICE_ID FROM bank_solvd.ACCOUNTS_MAIN_TABLE";

    private Connection conn;

    public MySQLAccountsMainDAO() throws SQLException {
        this.conn = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public void insert(AccountsMain a) {
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setLong(1, a.getClient().getId());
            stat.setLong(2, a.getCertificateDeposit().getId());
            stat.setLong(3, a.getSaving().getId());
            stat.setLong(4, a.getChecking().getId());
            stat.setDouble(5, a.getBalance());
            stat.setLong(4, a.getOffice().getId());
            stat.executeUpdate();
            LOGGER.info("Account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(AccountsMain a) {
        LOGGER.info("Updating Account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setLong(1, a.getClient().getId());
            stat.setLong(2, a.getCertificateDeposit().getId());
            stat.setLong(3, a.getSaving().getId());
            stat.setLong(4, a.getChecking().getId());
            stat.setDouble(5, a.getBalance());
            stat.setLong(4, a.getOffice().getId());
            stat.setLong(6, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(AccountsMain a) {
        LOGGER.info("Deleting Account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setLong(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public AccountsMain selectOne(int id) {
        AccountsMain a = new AccountsMain();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            a.setId(rs.getInt("ACCOUNT_ID"));
            a.setBalance(rs.getDouble("BALANCE"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    @Override
    public List<AccountsMain> selectAll() {
        //still not finished
        return null;
    }

}

