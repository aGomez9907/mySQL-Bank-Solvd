package com.solvd.laba.dao;

import com.solvd.laba.models.CreditCard;

import java.util.List;

public interface ICreditCardDAO extends IGenericDAO<CreditCard> {
    void insert(CreditCard t);

    void update(CreditCard t);

    void delete(CreditCard t);

    CreditCard selectOne(int id);

    List<CreditCard> selectAll();

    CreditCard getCreditCardByCheckingAccountId(int id);
}

