package com.solvd.laba.dao;

import com.solvd.laba.models.DebitCard;

import java.util.List;

public interface IDebitCardDAO extends IGenericDAO<DebitCard> {

    void insert(DebitCard t);

    void update(DebitCard t);

    void delete(DebitCard t);

    DebitCard selectOne(int id);

    List<DebitCard> selectAll();

    DebitCard getDebitCardByCheckingAccountId(int id);
}
