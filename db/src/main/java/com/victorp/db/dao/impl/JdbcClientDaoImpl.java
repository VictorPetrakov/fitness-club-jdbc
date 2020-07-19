package com.victorp.db.dao.impl;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.ClientDao;
import com.victorp.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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
                        client.setId(res.getLong("id_clients"));
                        client.setLogin(res.getString("login"));
                        client.setPassword(res.getString("password"));
                        client.setFirstName(res.getString("firstname"));
                        client.setLastName(res.getString("lastname"));
                        client.setBirthdate(res.getString("birthdate"));
                        client.setEmail(res.getString("email"));
                        client.setGroups(res.getString("groups"));
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
                fillClientQuery(item, ps);
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
                        client.setId(res.getLong("id_clients"));
                        client.setLogin(res.getString("login"));
                        client.setPassword(res.getString("password"));
                        client.setFirstName(res.getString("firstname"));
                        client.setLastName(res.getString("lastname"));
                        client.setBirthdate(res.getString("birthdate"));
                        client.setEmail(res.getString("email"));
                        client.setGroups(res.getString("groups"));
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
    public Client get(Long id) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM clients WHERE id = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return fillClient(rs);
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get contact by id", e);
            throw new Exception("Error during get contact by id", e);
        }

    }

    @Override
    public List<Client> getAll() throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (Statement s = c.createStatement()) {
                try (ResultSet rs = s.executeQuery("SELECT * FROM clients")) {
                    List<Client> clientList = new ArrayList<>();
                    while (rs.next()) {
                        final Client client = fillClient(rs);
                        clientList.add(client);
                    }
                    return clientList;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get all contacts", e);
            throw new Exception("Error during get all contacts", e);
        }

    }


    @Override
    public void update(Client item) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE `clients` SET " +
                            "login = ?, password = ?, firstName = ?, lastName= ?" +
                            "WHERE id = ?")) {
                fillClientQuery(item, ps);
                ps.setLong(5, item.getId());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            String msg = "Error during update contact";
            LOGGER.error(msg, e);
            throw new Exception(msg, e);
        }

    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM `clients` WHERE id = ?")) {
                ps.setLong(1, id);
                final int i = ps.executeUpdate(); // 1 or 0;
                if (i == 0) {
                    throw new Exception("No contact for id=" + id);
                }
            }
        } catch (Exception e) {
            String msg = "Error during delete contact";
            LOGGER.error(msg, e);
            throw new Exception(msg, e);
        }

    }

    private Client fillClient(ResultSet rs) throws SQLException {
        final Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setLogin(rs.getString("login"));
        client.setPassword(rs.getString("password"));
        client.setFirstName(rs.getString("firstName"));
        client.setLastName(rs.getString("lastName"));
        client.setBirthdate(rs.getString("birthdate"));
        client.setEmail(rs.getString("email"));
        client.setGroups(rs.getString("groups"));
        return client;
    }


    private void fillClientQuery(Client client, PreparedStatement ps) throws SQLException {
        ps.setString(1, client.getLogin());
        ps.setString(2, client.getPassword());
        ps.setString(3, client.getFirstName());
        ps.setString(4, client.getLastName());
        ps.setString(5, client.getBirthdate());
        ps.setString(6, client.getEmail());
    }

}
