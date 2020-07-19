package com.victorp.services.impl;

import com.victorp.db.dao.impl.JdbcAdminDaoImpl;
import com.victorp.db.dao.impl.JdbcClientDaoImpl;
import com.victorp.db.dao.impl.JdbcTrainerDaoImpl;
import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;
import com.victorp.services.RegistrationService;

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
    public void createTrainer(Trainer trainer) throws Exception {
        JdbcTrainerDaoImpl jdbcTrainerDaoImpl = new JdbcTrainerDaoImpl();
        jdbcTrainerDaoImpl.create(trainer);
    }

    @Override
    public void createAdmin(Admin admin) throws Exception {
        JdbcAdminDaoImpl jdbcAdminDaoImpl = new JdbcAdminDaoImpl();
        jdbcAdminDaoImpl.create(admin);
    }
}
