package com.solvd.laba.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    void insert(T t);

    void update(T t);

    void delete(T t);

    T selectOne(int id);

    List<T> selectAll();
}