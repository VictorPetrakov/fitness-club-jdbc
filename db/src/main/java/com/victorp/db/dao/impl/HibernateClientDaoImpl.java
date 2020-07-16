package com.victorp.db.dao.impl;

import com.victorp.db.dao.ClientDao;
import com.victorp.model.Client;

import java.util.List;

public class HibernateClientDaoImpl implements ClientDao {
    @Override
    public Client getByLogin(String login) {
        return null;
    }

    @Override
    public Client signUp(String login, String password) throws Exception {
        return null;
    }

    @Override
    public Client get(Long id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public void create(Client item) {

    }

    @Override
    public void update(Client item) {

    }

    @Override
    public void delete(Long id) {

    }
}
