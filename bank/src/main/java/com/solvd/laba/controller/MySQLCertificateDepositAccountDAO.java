package com.solvd.laba.controller;

import com.solvd.laba.dao.ICertificateDepositAccountDAO;
import com.solvd.laba.models.CertificateDepositAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLCertificateDepositAccountDAO extends MySQLDAO implements ICertificateDepositAccountDAO {
    private final static String CERTIFICATE_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.CERTIFICATE_DEPOSIT_ACCOUNT.* FROM CERTIFICATE_DEPOSIT_ACCOUNT INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON CERTIFICATE_DEPOSIT_ACCOUNT.CERTIFICATE_DEPOSIT_ACCOUNT_ID = ACCOUNTS_MAIN_TABLE.CERTIFICATE_DEPOSIT_ACCOUNT_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";
    private Connection conn;


    public CertificateDepositAccount getCertificateDepositAccountByAccountsMainId(int accountId) {
        CertificateDepositAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    CERTIFICATE_BY_ACCOUNTS_MAIN_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            c = new CertificateDepositAccount();
            c.setId(resultSet.getInt("CERTIFICATE_DEPOSIT_ACCOUNT_ID"));
            c.setStartDate(resultSet.getDate("START_DATE"));
            c.setFinishDate(resultSet.getDate("FINISH_DATE"));
            c.setInterestRate(resultSet.getDouble("INTEREST_RATE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(CertificateDepositAccount certificateDepositAccount) {
        //not finished
    }

    @Override
    public void update(CertificateDepositAccount certificateDepositAccount) {
        //not finished
    }

    @Override
    public void delete(CertificateDepositAccount certificateDepositAccount) {
        //not finished
    }

    @Override
    public CertificateDepositAccount selectOne(int id) {
        //not finished
        return null;
    }

    @Override
    public List<CertificateDepositAccount> selectAll() {
        //not finished
        return null;
    }
}
