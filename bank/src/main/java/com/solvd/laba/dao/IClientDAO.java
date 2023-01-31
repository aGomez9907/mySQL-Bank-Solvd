package com.solvd.laba.dao;

import com.solvd.laba.models.Client;

import java.util.List;

public interface IClientDAO {

    Client insert(Client client);

    void update(Client t);

    void delete(Client t);

    Client selectOne(int id);

    List<Client> selectAll();
}
