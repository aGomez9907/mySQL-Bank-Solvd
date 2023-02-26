package com.solvd.laba.service;

import com.solvd.laba.controller.*;
import com.solvd.laba.models.Client;

import java.sql.SQLException;

public class ClientsService {
    private final MySQLClientDAO clientDAO = new MySQLClientDAO();
    private final OfficeService officeService;
    private final MySQLCertificateDepositAccountDAO certificateDepositAccountDAO;
    private final CheckingAccountService checkingAccountService;
    private final MySQLCreditSummaryDAO creditSummaryDAO;
    private final MySQLSavingAccountDAO savingAccountDAO;
    private final MySQLHomeBankingDAO homeBankingDAO;

    public ClientsService() throws SQLException {
        this.certificateDepositAccountDAO = new MySQLCertificateDepositAccountDAO();
        this.savingAccountDAO = new MySQLSavingAccountDAO();
        this.checkingAccountService = new CheckingAccountService();
        this.officeService = new OfficeService();
        this.homeBankingDAO = new MySQLHomeBankingDAO();
        this.creditSummaryDAO = new MySQLCreditSummaryDAO();
    }


    public Client selectOne(int id) {
        Client client = clientDAO.selectOne(id);
        client.setCertificateDepositAccount(certificateDepositAccountDAO.getCertificateDepositAccountByClientId(id));
        client.setSavingAccount(savingAccountDAO.getSavingAccountByClientId(id));
        client.setCheckingAccount(checkingAccountService.getCheckingAccountByClientId(id));
        client.setOffice(officeService.getOfficeByClientId(id));
        client.setHomeBanking(homeBankingDAO.getHomeBankingByClientId(id));
        client.setCreditSummary(creditSummaryDAO.getCreditSummaryByClientId(id));
        return client;
    }

    public Client insert(Client c) throws SQLException {
        certificateDepositAccountDAO.insert(c.getCertificateDepositAccount());
        savingAccountDAO.insert(c.getSavingAccount());
        checkingAccountService.insert(c.getCheckingAccount());
        officeService.insert(c.getOffice());
        homeBankingDAO.insert(c.getHomeBanking());
        creditSummaryDAO.insert(c.getCreditSummary());
        return clientDAO.insert(c);
    }

    public void update(Client c) throws SQLException {
        certificateDepositAccountDAO.update(c.getCertificateDepositAccount());
        savingAccountDAO.update(c.getSavingAccount());
        checkingAccountService.update(c.getCheckingAccount());
        officeService.update(c.getOffice());
        homeBankingDAO.update(c.getHomeBanking());
        creditSummaryDAO.update(c.getCreditSummary());
        clientDAO.update(c);
    }
}
