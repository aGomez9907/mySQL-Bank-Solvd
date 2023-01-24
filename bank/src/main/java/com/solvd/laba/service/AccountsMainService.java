package com.solvd.laba.service;

import com.solvd.laba.controller.*;
import com.solvd.laba.models.*;

import java.sql.SQLException;

public class AccountsMainService {
    private MySQLAccountsMainDAO accountsMainDAO = new MySQLAccountsMainDAO();
    private MySQLClientDAO clientDAO;
    private MySQLCertificateDepositAccountDAO certificateDepositDAO;
    private MySQLSavingAccountDAO savingDAO;
    private MySQLCheckingAccountDAO checkingDAO;
    private MySQLBankBranchOfficeDAO officeDAO;

    public AccountsMainService() throws SQLException {
        this.clientDAO = new MySQLClientDAO();
        this.certificateDepositDAO = new MySQLCertificateDepositAccountDAO();
        this.savingDAO = new MySQLSavingAccountDAO();
        this.checkingDAO = new MySQLCheckingAccountDAO();
        this.officeDAO = new MySQLBankBranchOfficeDAO();
    }


    public AccountsMain selectOne(int id) {
        AccountsMain accountsMain = accountsMainDAO.selectOne(id);
        accountsMain.setClient(clientDAO.getClientByAccountsMainId(id));
        accountsMain.setCertificateDeposit(certificateDepositDAO.getCertificateDepositAccountByAccountsMainId(id));
        accountsMain.setSaving(savingDAO.getSavingAccountByAccountsMainId(id));
        accountsMain.setChecking(checkingDAO.getCheckingAccountByAccountsMainId(id));
        accountsMain.setOffice(officeDAO.getOfficeByAccountsMainId(id));

        return accountsMain;
    }

}
