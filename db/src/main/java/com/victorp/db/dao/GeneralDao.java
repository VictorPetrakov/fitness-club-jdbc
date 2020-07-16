package com.victorp.db.dao;

import java.util.List;

public interface GeneralDao <T>{

    T get(Long id) throws Exception;

    List<T> getAll() throws Exception;

    void create(T item) throws Exception;

    void update(T item) throws Exception;

    void delete(Long id) throws Exception;


}
