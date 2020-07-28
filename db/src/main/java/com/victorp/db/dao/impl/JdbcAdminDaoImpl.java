package com.victorp.db.dao.impl;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.AdminDao;
import com.victorp.model.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAdminDaoImpl implements AdminDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcAdminDaoImpl.class);
    private static AdminDao adminDao;

    public JdbcAdminDaoImpl() {
    }

    public static AdminDao getInstance() {
        if (adminDao == null) {
            synchronized (JdbcAdminDaoImpl.class) {
                if (adminDao == null) {
                    adminDao = new JdbcAdminDaoImpl();

                }
            }
        }
        return adminDao;
    }

    @Override
    public Admin signUp(String login, String password) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM `admin` WHERE login = ? AND password = ?")) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Admin admin = new Admin();
                        admin.setId(res.getLong("id_admin"));
                        admin.setLogin(res.getString("login"));
                        admin.setPassword(res.getString("password"));
                        admin.setFirstName(res.getString("firstname"));
                        admin.setLastName(res.getString("lastname"));
                        admin.setBirthdate(res.getString("birthdate"));
                        admin.setEmail(res.getString("email"));
                        return admin;
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
    public Admin getByLogin(String login) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM admin WHERE login = ?")) {
                ps.setString(1, login);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Admin admin = new Admin();
                        admin.setId(res.getLong("id"));
                        admin.setLogin(res.getString("login"));
                        admin.setPassword(res.getString("password"));
                        admin.setFirstName(res.getString("firstname"));
                        admin.setLastName(res.getString("lastname"));
                        admin.setBirthdate(res.getString("birthdate"));
                        admin.setEmail(res.getString("email"));
                        return admin;
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
    public Admin get(Long id) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM `admin` WHERE id = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return fillAdmin(rs);
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
    public List<Admin> getAll() throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (Statement s = c.createStatement()) {
                try (ResultSet rs = s.executeQuery("SELECT * FROM fitness_club_db.admin")) {
                    List<Admin> adminList = new ArrayList<>();
                    while (rs.next()) {
                        final Admin admin = fillAdmin(rs);
                        adminList.add(admin);
                    }
                    return adminList;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get all contacts", e);
            throw new Exception("Error during get all contacts", e);
        }

    }

    @Override
    public void create(Admin admin) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO `admin` (login, password, firstname, lastname, birthdate, email, id_role_admin)" +
                    "VALUES(?, ?, ?, ?, ?, ?, 1)")) {
                fillAdminQuery(admin, ps);
                ps.executeUpdate();

            }
        }

    }

    @Override
    public void update(Admin item) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE `admin` SET " +
                            "login = ?, password = ?, firstName = ?, lastName= ?" +
                            "WHERE id = ?")) {
                fillAdminQuery(item, ps);
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
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM `admin` WHERE id = ?")) {
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

    private Admin fillAdmin(ResultSet rs) throws SQLException {
        final Admin admin = new Admin();
        admin.setId(rs.getLong("id_admin"));
        admin.setLogin(rs.getString("login"));
        admin.setPassword(rs.getString("password"));
        admin.setFirstName(rs.getString("firstName"));
        admin.setLastName(rs.getString("lastName"));
        admin.setBirthdate(rs.getString("birthdate"));
        admin.setEmail(rs.getString("email"));
        return admin;
    }

    private void fillAdminQuery(Admin admin, PreparedStatement ps) throws SQLException {
        ps.setString(1, admin.getLogin());
        ps.setString(2, admin.getPassword());
        ps.setString(3, admin.getFirstName());
        ps.setString(4, admin.getLastName());
        ps.setString(5, admin.getBirthdate());
        ps.setString(6, admin.getEmail());
    }
}
