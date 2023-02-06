package com.solvd.laba.dao;

import com.solvd.laba.models.CheckingAccount;

public interface ICheckingAccountDAO extends IGenericDAO<CheckingAccount> {
    CheckingAccount getCheckingAccountByClientId(int id);
}
