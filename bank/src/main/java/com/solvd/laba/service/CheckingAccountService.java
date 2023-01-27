package com.solvd.laba.service;

import com.solvd.laba.controller.MySQLCheckingAccountDAO;
import com.solvd.laba.controller.MySQLCreditCardDAO;
import com.solvd.laba.controller.MySQLDebitCardDAO;
import com.solvd.laba.models.*;
import com.solvd.laba.models.CheckingAccount;

import java.sql.SQLException;

public class CheckingAccountService {
    private MySQLCheckingAccountDAO checkingAccountDAO = new MySQLCheckingAccountDAO();
    private MySQLCreditCardDAO creditCardDAO = new MySQLCreditCardDAO();
    private MySQLDebitCardDAO debitCardDAO = new MySQLDebitCardDAO();

    public CheckingAccountService() throws SQLException {
    }

    public CheckingAccountService(MySQLCreditCardDAO creditCardDAO, MySQLDebitCardDAO debitCardDAO) throws SQLException {
        this.creditCardDAO = creditCardDAO;
        this.debitCardDAO = debitCardDAO;
    }

    public CheckingAccount selectOne(int checkingAccountId) {
        CheckingAccount checkingAccount = checkingAccountDAO.selectOne(checkingAccountId);
        checkingAccount.setDebitCard(debitCardDAO.getDebitCardByCheckingAccountId(checkingAccountId));
        checkingAccount.setCreditCard(creditCardDAO.getCreditCardByCheckingAccountId(checkingAccountId));
        return checkingAccount;
    }


    public CheckingAccount insert(CheckingAccount checkingAccount, CreditCard creditCard, DebitCard debitCard) throws SQLException {
        CheckingAccount checkingAccount1 = new CheckingAccount();
        checkingAccount1.setChecks(checkingAccount.getChecks());
        checkingAccount1.setBalance(checkingAccount.getBalance());
        creditCardDAO.insert(creditCard);
        checkingAccount1.setCreditCard(creditCard);
        debitCardDAO.insert(debitCard);
        checkingAccount1.setDebitCard(debitCard);

        checkingAccountDAO.insert(checkingAccount1);
        return checkingAccount;
    }


    public void update(CheckingAccount checkingAccount, CreditCard creditCard, DebitCard debitCard) throws SQLException {
        CheckingAccount checkingAccount1 = checkingAccountDAO.selectOne(checkingAccount.getId());
        checkingAccount1.setCreditCard(creditCard);
        creditCardDAO.insert(creditCard);
        checkingAccount1.setDebitCard(debitCard);
        debitCardDAO.insert(debitCard);
        checkingAccount1.setChecks(checkingAccount.getChecks());
        checkingAccount1.setBalance(checkingAccount.getBalance());
        checkingAccountDAO.update(checkingAccount1);
    }

}
