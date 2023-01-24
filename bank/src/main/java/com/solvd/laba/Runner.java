package com.solvd.laba;

import com.solvd.laba.controller.*;
import com.solvd.laba.models.*;
import com.solvd.laba.service.AccountsMainService;
import com.solvd.laba.utils.ConnectionPool;

import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance().getConnection();


        MySQLAccountsMainDAO accountsMainDAO = new MySQLAccountsMainDAO();
        MySQLClientDAO clientDAO = new MySQLClientDAO();
        MySQLCertificateDepositAccountDAO certificateDepositDAO = new MySQLCertificateDepositAccountDAO();
        MySQLSavingAccountDAO savingDAO = new MySQLSavingAccountDAO();
        MySQLCheckingAccountDAO checkingDAO = new MySQLCheckingAccountDAO();
        MySQLBankBranchOfficeDAO officeDAO = new MySQLBankBranchOfficeDAO();
        AccountsMainService as = new AccountsMainService();

        try {
            Client c = new Client("alejo", "gomez", 23, "argentina", "st 123");
            CertificateDepositAccount cd = new CertificateDepositAccount();
            SavingAccount s = new SavingAccount();
            CheckingAccount ch = new CheckingAccount(5);
            BankBranchOffice office = new BankBranchOffice(1000, "st 123", "argentina");
            AccountsMain am = new AccountsMain(c, cd, s, ch, 100000, office);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
