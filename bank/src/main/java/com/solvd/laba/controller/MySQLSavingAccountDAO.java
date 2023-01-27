package com.solvd.laba.controller;

import com.solvd.laba.dao.ISavingAccountDAO;
import com.solvd.laba.models.CertificateDepositAccount;
import com.solvd.laba.models.SavingAccount;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLSavingAccountDAO extends MySQLDAO implements ISavingAccountDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String SAVING_BY_CLIENT_ID = "SELECT bank_solvd.SAVING_ACCOUNT.* FROM SAVING_ACCOUNT INNER JOIN bank_solvd.CLIENT ON SAVING_ACCOUNT.SAVING_ACCOUNT_ID = CLIENT.SAVING_ACCOUNT_ID WHERE CLIENT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.SAVING_ACCOUNT (MONTH_WITHDRAWALS, BALANCE) VALUES (?, ?)";
    final String UPDATE = "UPDATE bank_solvd.SAVING_ACCOUNT SET MONTH_WITHDRAWALS = ?, BALANCE = ? WHERE SAVING_ACCOUNT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.SAVING_ACCOUNT WHERE SAVING_ACCOUNT_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.SAVING_ACCOUNT WHERE SAVING_ACCOUNT_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.SAVING_ACCOUNT";


    public MySQLSavingAccountDAO() throws SQLException {

    }

    public SavingAccount getSavingAccountByClientId(int accountId) {
        SavingAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    SAVING_BY_CLIENT_ID);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            c = new SavingAccount();
            while (rs.next()) {
                c.setId(rs.getInt("SAVING_ACCOUNT_ID"));
                c.setMonthWithdrawals(rs.getInt("MONTH_WITHDRAWALS"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


    @Override
    public void insert(SavingAccount a) {
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setInt(1, a.getMonthWithdrawals());
            stat.setDouble(2, a.getBalance());
            stat.executeUpdate();
            LOGGER.info("Saving account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(SavingAccount a) {
        LOGGER.info("Updating saving account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(1, a.getMonthWithdrawals());
            stat.setDouble(2, a.getBalance());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(SavingAccount a) {
        LOGGER.info("Deleting saving account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public SavingAccount selectOne(int id) {
        SavingAccount c = new SavingAccount();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("SAVING_ACCOUNT_ID"));
                c.setMonthWithdrawals(rs.getInt("MONTH_WITHDRAWALS"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<SavingAccount> selectAll() {
        ArrayList<SavingAccount> sa = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sa.add(new SavingAccount(rs.getInt("SAVING_ACCOUNT_ID"), rs.getInt("MONTH_WITHDRAWALS"), rs.getDouble("BALANCE")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return sa;
    }
}
