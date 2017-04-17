package com.olegnikitin.dao;

import java.util.List;

/**
 * Created by olegnikitindev on 06.03.2017.
 */
public interface Dao<T> {
    List<T> findAll();
    T find(String id);
    void create(T t);
    void update(T t);
    void delete(T t);
}
