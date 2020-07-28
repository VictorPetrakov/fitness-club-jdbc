package com.victorp.db.dao.impl;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.dao.TrainerDao;
import com.victorp.db.dao.exception.DAOException;
import com.victorp.model.Trainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTrainerDaoImpl implements TrainerDao {
    private static final Logger LOGGER = LogManager.getLogger(JdbcTrainerDaoImpl.class);
    private static TrainerDao trainerDao;

    public JdbcTrainerDaoImpl() {
    }

    public static TrainerDao getInstance() {
        if (trainerDao == null) {
            synchronized (JdbcTrainerDaoImpl.class) {
                if (trainerDao == null) {
                    trainerDao = new JdbcTrainerDaoImpl();

                }
            }
        }
        return trainerDao;
    }


    @Override
    public Trainer signUp(String login, String password) throws DAOException {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE login = ? AND password = ?")) {
                ps.setString(1, login);
                ps.setString(2, password);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Trainer trainer = new Trainer();
                        trainer.setId(res.getLong("id_trainers"));
                        trainer.setLogin(res.getString("login"));
                        trainer.setPassword(res.getString("password"));
                        trainer.setFirstName(res.getString("firstname"));
                        trainer.setLastName(res.getString("lastname"));
                        trainer.setBirthdate(res.getString("birthdate"));
                        trainer.setEmail(res.getString("email"));
                        trainer.setGroup(res.getString("groups"));
                        return trainer;
                    } else {
                        return null;
                    }

                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get client signIN", e);
            throw new DAOException("Ошибка при аунтификации", e);
        }

    }

    @Override
    public Trainer getByLogin(String login) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE login = ?")) {
                ps.setString(1, login);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Trainer trainer = new Trainer();
                        trainer.setId(res.getLong("id_trainers"));
                        trainer.setLogin(res.getString("login"));
                        trainer.setPassword(res.getString("password"));
                        trainer.setFirstName(res.getString("firstname"));
                        trainer.setLastName(res.getString("lastname"));
                        trainer.setGroup(res.getString("groups"));
                        return trainer;
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
    public Trainer getByGroup(String group) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE groups = ?")) {
                ps.setString(1, group);
                try (ResultSet res = ps.executeQuery()) {
                    if (res.next()) {
                        final Trainer trainer = new Trainer();
                        trainer.setId(res.getLong("id_trainers"));
                        trainer.setLogin(res.getString("login"));
                        trainer.setPassword(res.getString("password"));
                        trainer.setFirstName(res.getString("firstname"));
                        trainer.setLastName(res.getString("lastname"));
                        trainer.setEmail(res.getString("email"));
                        trainer.setGroup(res.getString("groups"));
                        return trainer;
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
    public Trainer get(Long id) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM trainers WHERE id = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return fillTrainer(rs);
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get contact by id", e);
            throw new DAOException("Error during get contact by id", e);
        }
    }

    @Override
    public List<Trainer> getAll() throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (Statement s = c.createStatement()) {
                try (ResultSet rs = s.executeQuery("SELECT * FROM fitness_club_db.trainers")) {
                    List<Trainer> trainerList = new ArrayList<>();
                    while (rs.next()) {
                        final Trainer trainer = fillTrainer(rs);
                        trainerList.add(trainer);
                    }
                    return trainerList;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error during get all contacts", e);
            throw new Exception("Error during get all contacts", e);
        }
    }

    @Override
    public void create(Trainer trainer) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO `trainers` (`login`,`password`,`firstname`,`lastname`,`birthdate`,`email`,`groups`,`id_role_trainer`)" +
                    "VALUES(?,?,?,?,?,?,?,2)")) {
                fillTrainerQuery(trainer, ps);
                ps.executeUpdate();

            }

        } catch (Exception e) {
            LOGGER.error("Error during create contact", e);
            throw new Exception("Error during create contact", e);
        }
    }

    @Override
    public void update(Trainer item) throws Exception {
        try (Connection c = JdbcProvider.getConnection()) {
            try (PreparedStatement ps = c.prepareStatement(
                    "UPDATE `trainers` SET " +
                            "login = ?, password = ?, firstName = ?, lastName= ?" +
                            "WHERE id = ?")) {
                fillTrainerQuery(item, ps);
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
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM `trainers` WHERE id = ?")) {
                ps.setLong(1, id);
                final int i = ps.executeUpdate(); // 1 or 0;
                if (i == 0) {
                    throw new Exception("No trainer for id=" + id);
                }
            }
        } catch (Exception e) {
            String msg = "Error during delete contact";
            LOGGER.error(msg, e);
            throw new Exception(msg, e);
        }
    }
    private Trainer fillTrainer(ResultSet rs) throws SQLException {
        final Trainer trainer = new Trainer();
        trainer.setId(rs.getLong("id_trainers"));
        trainer.setLogin(rs.getString("login"));
        trainer.setPassword(rs.getString("password"));
        trainer.setFirstName(rs.getString("firstName"));
        trainer.setLastName(rs.getString("lastName"));
        trainer.setBirthdate(rs.getString("birthdate"));
        trainer.setEmail(rs.getString("email"));
        trainer.setGroup(rs.getString("groups"));
        return trainer;
    }

    private void fillTrainerQuery(Trainer trainer, PreparedStatement ps) throws SQLException {
        ps.setString(1, trainer.getLogin());
        ps.setString(2, trainer.getPassword());
        ps.setString(3, trainer.getFirstName());
        ps.setString(4, trainer.getLastName());
        ps.setString(5, trainer.getBirthdate());
        ps.setString(6, trainer.getEmail());
        ps.setString(7, trainer.getGroup());


    }
}
