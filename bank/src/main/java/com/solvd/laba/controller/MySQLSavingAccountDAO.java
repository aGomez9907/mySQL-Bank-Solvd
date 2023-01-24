package com.solvd.laba.controller;

import com.solvd.laba.dao.ISavingAccountDAO;
import com.solvd.laba.models.SavingAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLSavingAccountDAO extends MySQLDAO implements ISavingAccountDAO {

    private final static String SAVING_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.SAVING_ACCOUNT.* FROM SAVING_ACCOUNT INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON SAVING_ACCOUNT.SAVING_ACCOUNT_ID = ACCOUNTS_MAIN_TABLE.SAVING_ACCOUNT_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";
    private Connection conn;


    public SavingAccount getSavingAccountByAccountsMainId(int accountId) {
        SavingAccount c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    SAVING_BY_ACCOUNTS_MAIN_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            c = new SavingAccount();
            c.setId(resultSet.getInt("SAVING_ACCOUNT_ID"));
            c.setMonthWithdrawals(resultSet.getInt("MONTH_WITHDRAWALS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


    @Override
    public void insert(SavingAccount savingAccount) {
        //not finished
    }

    @Override
    public void update(SavingAccount savingAccount) {
        //not finished
    }

    @Override
    public void delete(SavingAccount savingAccount) {
        //not finished
    }

    @Override
    public SavingAccount selectOne(int id) {
        //not finished
        return null;
    }

    @Override
    public List<SavingAccount> selectAll() {
        //not finished
        return null;
    }
}
