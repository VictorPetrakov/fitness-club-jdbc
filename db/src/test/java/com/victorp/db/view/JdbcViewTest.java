package com.victorp.db.view;

import com.victorp.db.connection.JdbcProvider;
import com.victorp.db.connection.JdbcProviderTest;
import com.victorp.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JdbcViewTest {
    private static final Logger LOGGER = LogManager.getLogger(JdbcViewTest.class);

    @Test
    public void TestView() throws Exception {
        try (final Connection connection = JdbcProvider.getConnection()) {
            try (final Statement statement = connection.createStatement()) {
                try (final ResultSet resultSet = statement.executeQuery("SELECT * FROM fitness_club_db.clients ")) {
                    List<Client> clientList = new ArrayList<>();
                    while (resultSet.next()) {
                        final Client client = fillClient(resultSet);
                        clientList.add(client);
                    }
                    LOGGER.info("Clients: {} {} {}",
                            clientList.get(1),
                            clientList.get(2),
                            clientList.get(3));
                }
            }

        }
    }
    private Client fillClient(ResultSet rs) throws SQLException {
        final Client client = new Client();
        client.setId(rs.getLong("id_clients"));
        client.setLogin(rs.getString("login"));
        client.setPassword(rs.getString("password"));
        client.setFirstName(rs.getString("firstName"));
        client.setLastName(rs.getString("lastName"));
        client.setBirthdate(rs.getString("birthdate"));
        client.setEmail(rs.getString("email"));
        client.setGroups(rs.getString("groups"));
        return client;
    }

}

