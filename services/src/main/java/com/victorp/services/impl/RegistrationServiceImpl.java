package com.victorp.services.impl;


import com.victorp.db.dao.impl.JdbcClientDaoImpl;
import com.victorp.model.Client;
import com.victorp.services.RegistrationService;

import java.sql.Date;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    private static RegistrationService instance;

    public static RegistrationService getInstance() {

        if (instance == null) {
            synchronized (RegistrationService.class) {
                if (instance == null) {
                    instance = new RegistrationServiceImpl();
                }
            }
        }
        return instance;
    }

    private RegistrationServiceImpl() {
    }

    @Override
    public void createClient(Client client) throws Exception {

        JdbcClientDaoImpl jdbcClientDaoImpl = new JdbcClientDaoImpl();
        jdbcClientDaoImpl.create(client);

    }

    @Override
    public void createTrainer() {

    }

    @Override
    public void createAdmin() {

    }
}
