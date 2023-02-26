package com.solvd.laba.service;

import com.solvd.laba.controller.MySQLCheckingAccountDAO;
import com.solvd.laba.controller.MySQLCreditCardDAO;
import com.solvd.laba.controller.MySQLDebitCardDAO;
import com.solvd.laba.models.CheckingAccount;

import java.sql.SQLException;

public class CheckingAccountService {
    private final MySQLCheckingAccountDAO checkingAccountDAO = new MySQLCheckingAccountDAO();
    private final MySQLCreditCardDAO creditCardDAO;
    private final MySQLDebitCardDAO debitCardDAO;

    public CheckingAccountService() throws SQLException {
        this.creditCardDAO = new MySQLCreditCardDAO();
        this.debitCardDAO = new MySQLDebitCardDAO();
    }


    public CheckingAccount selectOne(int checkingAccountId) {
        CheckingAccount checkingAccount = checkingAccountDAO.selectOne(checkingAccountId);
        checkingAccount.setDebitCard(debitCardDAO.getDebitCardByCheckingAccountId(checkingAccountId));
        checkingAccount.setCreditCard(creditCardDAO.getCreditCardByCheckingAccountId(checkingAccountId));
        return checkingAccount;
    }


    public void insert(CheckingAccount checkingAccount) throws SQLException {
        creditCardDAO.insert(checkingAccount.getCreditCard());
        debitCardDAO.insert(checkingAccount.getDebitCard());
        checkingAccountDAO.insert(checkingAccount);
    }


    public void update(CheckingAccount checkingAccount) throws SQLException {
        creditCardDAO.update(checkingAccount.getCreditCard());
        debitCardDAO.update(checkingAccount.getDebitCard());
        checkingAccountDAO.update(checkingAccount);
    }


    public CheckingAccount getCheckingAccountByClientId(int id) {
        return checkingAccountDAO.getCheckingAccountByClientId(id);
    }

}
