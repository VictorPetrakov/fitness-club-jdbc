package com.victorp.db.dao;

import com.victorp.model.Client;

import java.sql.Date;

public interface ClientDao extends GeneralDao<Client> {

    Client getByLogin(String login) throws Exception ;
    Client signUp(String login, String password) throws Exception;
}
