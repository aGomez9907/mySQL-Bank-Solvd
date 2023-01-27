package com.solvd.laba.controller;

import com.solvd.laba.dao.ICertificateDepositAccountDAO;
import com.solvd.laba.models.BankBranchOffice;
import com.solvd.laba.models.CertificateDepositAccount;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCertificateDepositAccountDAO extends MySQLDAO implements ICertificateDepositAccountDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CERTIFICATE_BY_CLIENT_ID = "SELECT bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT.* FROM CERTIFICATE_DEPOSIT_ACCOUNT INNER JOIN bank_solvd.CLIENT ON CERTIFICATE_DEPOSIT_ACCOUNT.CERTIFICATE_DEPOSIT_ACCOUNT_ID = CLIENT.CERTIFICATE_DEPOSIT_ACCOUNT_ID WHERE CLIENT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT (START_DATE, FINISH_DATE, INTEREST_RATE, BALANCE) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT SET START_DATE = ?, FINISH_DATE = ?, INTEREST_RATE = ?, BALANCE = ? WHERE CERTIFICATE_DEPOSIT_ACCOUNT_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT WHERE CERTIFICATE_DEPOSIT_ACCOUNT_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT WHERE CERTIFICATE_DEPOSIT_ACCOUNT_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT";


    public MySQLCertificateDepositAccountDAO() throws SQLException {

    }

    public CertificateDepositAccount getCertificateDepositAccountByClientId(int accountId) {
        CertificateDepositAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    CERTIFICATE_BY_CLIENT_ID);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            c = new CertificateDepositAccount();
            while (rs.next()) {
                c.setId(rs.getInt("CERTIFICATE_DEPOSIT_ACCOUNT_ID"));
                c.setStartDate(rs.getDate("START_DATE"));
                c.setFinishDate(rs.getDate("FINISH_DATE"));
                c.setInterestRate(rs.getDouble("INTEREST_RATE"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(CertificateDepositAccount a) {
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setDate(1, a.getStartDate());
            stat.setDate(2, a.getFinishDate());
            stat.setDouble(3, a.getInterestRate());
            stat.setDouble(4, a.getBalance());
            stat.executeUpdate();
            LOGGER.info("Certificate deposit account created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CertificateDepositAccount a) {
        LOGGER.info("Updating certificate deposit account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setDate(1, a.getStartDate());
            stat.setDate(2, a.getFinishDate());
            stat.setDouble(3, a.getInterestRate());
            stat.setDouble(4, a.getBalance());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CertificateDepositAccount a) {
        LOGGER.info("Deleting certificate deposit account with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public CertificateDepositAccount selectOne(int id) {
        CertificateDepositAccount c = new CertificateDepositAccount();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("CERTIFICATE_DEPOSIT_ACCOUNT_ID"));
                c.setStartDate(rs.getDate("START_DATE"));
                c.setFinishDate(rs.getDate("FINISH_DATE"));
                c.setInterestRate(rs.getDouble("INTEREST_RATE"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<CertificateDepositAccount> selectAll() {
        ArrayList<CertificateDepositAccount> cda = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cda.add(new CertificateDepositAccount(rs.getInt("CERTIFICATE_DEPOSIT_ACCOUNT_ID"), rs.getDate("START_DATE"), rs.getDate("FINISH_DATE"), rs.getDouble("INTEREST_RATE"), rs.getDouble("BALANCE")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cda;
    }
}
