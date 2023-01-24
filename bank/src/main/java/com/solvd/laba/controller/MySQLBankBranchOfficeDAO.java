package com.solvd.laba.controller;

import com.solvd.laba.dao.IBankBranchOfficeDAO;
import com.solvd.laba.models.BankBranchOffice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLBankBranchOfficeDAO extends MySQLDAO implements IBankBranchOfficeDAO {

    private final static String OFFICE_BY_ACCOUNTS_MAIN_ID = "SELECT bank_solvd.BANK_BRANCH_OFFICE.* FROM BANK_BRANCH_OFFICE INNER JOIN bank_solvd.ACCOUNTS_MAIN_TABLE ON BANK_BRANCH_OFFICE.OFFICE_ID = ACCOUNTS_MAIN_TABLE.OFFICE_ID WHERE ACCOUNTS_MAIN_TABLE.ACCOUNT_ID = ?";
    private Connection conn;


    public BankBranchOffice getOfficeByAccountsMainId(int accountId) {
        BankBranchOffice c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    OFFICE_BY_ACCOUNTS_MAIN_ID);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            c = new BankBranchOffice();
            c.setId(resultSet.getInt("OFFICE_ID"));
            c.setGeneralBalance(resultSet.getDouble("GENERAL_BALANCE"));
            c.setCountry(resultSet.getString("COUNTRY"));
            c.setAddress(resultSet.getString("ADDRESS"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(BankBranchOffice bankBranchOffice) {
        //not finished
    }

    @Override
    public void update(BankBranchOffice bankBranchOffice) {
        //not finished
    }

    @Override
    public void delete(BankBranchOffice bankBranchOffice) {
        //not finished
    }

    @Override
    public BankBranchOffice selectOne(int id) {
        //not finished
        return null;
    }

    @Override
    public List<BankBranchOffice> selectAll() {
        //not finished
        return null;
    }
}
