package com.victorp.services.impl;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.impl.JdbcAdminDaoImpl;
import com.victorp.db.dao.impl.JdbcClientDaoImpl;
import com.victorp.db.dao.impl.JdbcTrainerDaoImpl;
import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;
import com.victorp.services.AuthorizationService;

import java.sql.Connection;

public class AuthorizationServiceImpl implements AuthorizationService {

    private static AuthorizationService instance;

    public static AuthorizationService getInstance() {

        if (instance == null) {
            synchronized (AuthorizationService.class) {
                if (instance == null) {
                    instance = new AuthorizationServiceImpl();
                }
            }
        }
        return instance;
    }

    private AuthorizationServiceImpl() {
    }


    @Override
    public Client authorizeClient(String login, String password) throws Exception {

        JdbcClientDaoImpl jdbcClientDaoImpl = new JdbcClientDaoImpl();
        Client client = jdbcClientDaoImpl.signUp(login, password);

        return client;
    }

    @Override
    public Trainer authorizeTrainer(String login, String password) throws Exception{

        JdbcTrainerDaoImpl jdbcTrainerDaoImpl = new JdbcTrainerDaoImpl();
        Trainer trainer = jdbcTrainerDaoImpl.signUp(login, password);

        return trainer;
    }

    @Override
    public Admin authorizeAdmin(String login, String password) throws Exception {
        JdbcAdminDaoImpl jdbcAdminDaoImpl = new JdbcAdminDaoImpl();
        Admin admin = jdbcAdminDaoImpl.signUp(login, password);

        return admin;
    }


}
