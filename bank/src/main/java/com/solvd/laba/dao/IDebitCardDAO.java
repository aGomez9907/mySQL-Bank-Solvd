package com.solvd.laba.dao;

import com.solvd.laba.models.DebitCard;

import java.util.List;

public interface IDebitCardDAO {

    DebitCard insert(DebitCard t);

    DebitCard update(DebitCard t);

    DebitCard delete(DebitCard t);

    DebitCard selectOne(int id);

    List<DebitCard> selectAll();

    DebitCard getDebitCardByCheckingAccountId(int id);
}
