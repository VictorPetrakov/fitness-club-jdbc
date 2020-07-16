package com.victorp.db.dao.impl;


import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.ClientDao;
import com.victorp.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class JdbcClientDaoImpl implements ClientDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcClientDaoImpl.class);
    private static ClientDao clientDao;

    public JdbcClientDaoImpl() {
    }

    public static ClientDao getInstance() {
        if (clientDao == null) {
            synchronized (JdbcClientDaoImpl.class) {
                if (clientDao == null) {
                    clientDao = new JdbcClientDaoImpl();

                }
            }
        }
        return clientDao;
    }

    @Override
    public Client signUp(String login, String password) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM clients WHERE login = ? AND password = ?")) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Client client = new Client();
                        client.setId(res.getLong("id"));
                        client.setLogin(res.getString("login"));
                        client.setPassword(res.getString("password"));
                        client.setFirstName(res.getString("firstname"));
                        client.setLastName(res.getString("lastname"));
                        client.setBirthdate(res.getString("birthdate"));
                        client.setEmail(res.getString("email"));
                        client.setGroups((List<String>) res.getArray("groups"));
                        return client;
                    } else {
                        return null;
                    }

                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get client by login", e);
            throw new Exception("sd", e);
        }

    }

    @Override
    public void create(Client item) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO clients (login, password, firstname, lastname, birthdate, email, id_role)" +
                    "VALUES(?, ?, ?, ?, ?, ?, 3)")) {
                fillContactQuery(item, ps);
                ps.executeUpdate();

            }
        }

    }

    @Override
    public Client getByLogin(String login) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM clients WHERE login = ?")) {
                ps.setString(1, login);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Client client = new Client();
                        client.setId(res.getLong("id"));
                        client.setLogin(res.getString("login"));
                        client.setPassword(res.getString("password"));
                        client.setFirstName(res.getString("firstname"));
                        client.setLastName(res.getString("lastname"));
                        client.setBirthdate(res.getString("birthdate"));
                        client.setEmail(res.getString("email"));
                        client.setGroups((List<String>) res.getArray("groups"));
                        return client;
                    } else {
                        return null;
                    }

                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get client by login", e);
            throw new Exception("sd", e);
        }

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
    public void update(Client item) {

    }

    @Override
    public void delete(Long id) {

    }

    private void fillContactQuery(Client client, PreparedStatement ps) throws SQLException {
        ps.setString(1, client.getLogin());
        ps.setString(2, client.getPassword());
        ps.setString(3, client.getFirstName());
        ps.setString(4, client.getLastName());
        ps.setString (5, client.getBirthdate());
        ps.setString(6, client.getEmail());
    }

}
