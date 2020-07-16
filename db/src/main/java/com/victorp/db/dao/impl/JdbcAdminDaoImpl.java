package com.victorp.db.dao.impl;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.AdminDao;
import com.victorp.db.dao.TrainerDao;
import com.victorp.model.Admin;
import com.victorp.model.Trainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class JdbcAdminDaoImpl implements AdminDao {

    private static final Logger LOGGER = LogManager.getLogger(JdbcAdminDaoImpl.class);
    private static AdminDao adminDao;

    public JdbcAdminDaoImpl(){}

    public static  AdminDao getInstance(){
        if(adminDao == null){
            synchronized (JdbcAdminDaoImpl.class){
                if(adminDao == null){
                    adminDao = new JdbcAdminDaoImpl();

                }
            }
        }
        return adminDao;
    }

    @Override
    public Admin signUp(String login, String password) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE login = ? AND password = ?")) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Admin admin = new Admin();
                        admin.setId(res.getLong("id"));
                        admin.setLogin(res.getString("login"));
                        admin.setPassword(res.getString("password"));
                        admin.setFirstName(res.getString("firstname"));
                        admin.setLastName(res.getString("lastname"));
                        admin.setBirthdate(res.getDate("birthdate"));
                        admin.setEmail(res.getString("email"));
                        admin.setGroup(res.getString("group"));
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
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE login = ?")) {
                ps.setString(1, login);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Admin admin = new Admin();
                        admin.setId(res.getLong("id"));
                        admin.setLogin(res.getString("login"));
                        admin.setPassword(res.getString("password"));
                        admin.setFirstName(res.getString("firstname"));
                        admin.setLastName(res.getString("lastname"));
                        admin.setBirthdate(res.getDate("birthdate"));
                        admin.setEmail(res.getString("email"));
                        admin.setGroup(res.getString("group"));
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
        return null;
    }

    @Override
    public List<Admin> getAll() throws Exception {
        return null;
    }

    @Override
    public void create(Admin item) throws Exception {

    }

    @Override
    public void update(Admin item) throws Exception {

    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
