package com.solvd.laba.service;

import com.solvd.laba.controller.*;
import com.solvd.laba.models.*;

import java.sql.SQLException;

public class ClientsService {
    private MySQLClientDAO clientDAO = new MySQLClientDAO();
    private MySQLBankBranchOfficeDAO officeDAO;
    private MySQLCertificateDepositAccountDAO certificateDepositAccountDAO;
    private MySQLCheckingAccountDAO checkingAccountDAO;
    private MySQLCreditSummaryDAO creditSummaryDAO;
    private MySQLSavingAccountDAO savingAccountDAO;
    private MySQLHomeBankingDAO homeBankingDAO;

    public ClientsService() throws SQLException {
        this.certificateDepositAccountDAO = new MySQLCertificateDepositAccountDAO();
        this.savingAccountDAO = new MySQLSavingAccountDAO();
        this.checkingAccountDAO = new MySQLCheckingAccountDAO();
        this.officeDAO = new MySQLBankBranchOfficeDAO();
        this.homeBankingDAO = new MySQLHomeBankingDAO();
        this.creditSummaryDAO = new MySQLCreditSummaryDAO();
    }


    public Client selectOne(int id) {
        Client client = clientDAO.selectOne(id);
        client.setCertificateDepositAccount(certificateDepositAccountDAO.getCertificateDepositAccountByClientId(id));
        client.setSavingAccount(savingAccountDAO.getSavingAccountByClientId(id));
        client.setCheckingAccount(checkingAccountDAO.getCheckingAccountByClientId(id));
        client.setOffice(officeDAO.getOfficeByClientId(id));
        client.setHomeBanking(homeBankingDAO.getHomeBankingByClientId(id));
        client.setCreditSummary(creditSummaryDAO.getCreditSummaryByClientId(id));
        return client;
    }

    public Client insert() {
        return null;
    }
}
