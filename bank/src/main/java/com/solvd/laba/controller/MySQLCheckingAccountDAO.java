package com.solvd.laba.controller;

import com.solvd.laba.dao.ICheckingAccountDAO;
import com.solvd.laba.models.CheckingAccount;
import com.solvd.laba.models.SavingAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLCheckingAccountDAO extends MySQLDAO implements ICheckingAccountDAO {


    private final String CHECKING_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.CHECKING_ACCOUNT.* FROM CHECKING_ACCOUNT INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON CHECKING_ACCOUNT.CHECKING_ACCOUNT_ID = ACCOUNTS_MAIN_TABLE.CHECKING_ACCOUNT_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";
    private Connection conn;


    public CheckingAccount getCheckingAccountByAccountsMainId(int accountId) {
        CheckingAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    CHECKING_BY_ACCOUNTS_MAIN_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            c = new CheckingAccount();
            c.setId(resultSet.getInt("CHECKING_ACCOUNT_ID"));
            c.setChecks(resultSet.getInt("CHECKS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(CheckingAccount checkingAccount) {
        //not finished
    }

    @Override
    public void update(CheckingAccount checkingAccount) {
        //not finished
    }

    @Override
    public void delete(CheckingAccount checkingAccount) {
        //not finished
    }

    @Override
    public CheckingAccount selectOne(int id) {
        //not finished
        return null;
    }

    @Override
    public List<CheckingAccount> selectAll() {
        //not finished
        return null;
    }
}
