package com.victorp.services.impl;

import com.victorp.db.dao.impl.JdbcAdminDaoImpl;
import com.victorp.db.dao.impl.JdbcClientDaoImpl;
import com.victorp.db.dao.impl.JdbcTrainerDaoImpl;
import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;
import com.victorp.services.ViewService;

import java.util.List;

public class ViewServiceImpl implements ViewService {

    private static ViewService instance;

    public static ViewService getInstance() {
        if (instance == null) {
            synchronized (ViewService.class) {
                if (instance == null) {
                    instance = new ViewServiceImpl();
                }
            }
        }
        return instance;
    }

    private ViewServiceImpl() {

    }

    @Override
    public List<Client> viewClients() throws Exception {
        JdbcClientDaoImpl jdbcClientDaoImpl = new JdbcClientDaoImpl();
        List<Client> clientList = jdbcClientDaoImpl.getAll();
        return clientList;
    }

    @Override
    public List<Admin> viewAdmins() throws Exception {
        JdbcAdminDaoImpl jdbcAdminDao = new JdbcAdminDaoImpl();
        List<Admin> adminList = jdbcAdminDao.getAll();
        return adminList;
    }

    @Override
    public List<Trainer> viewTrainers() throws Exception {
        JdbcTrainerDaoImpl jdbcTrainerDao = new JdbcTrainerDaoImpl();
        List<Trainer> trainerList = jdbcTrainerDao.getAll();
        return trainerList;
    }
}
