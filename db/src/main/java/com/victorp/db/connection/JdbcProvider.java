package com.victorp.db.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcProvider {

    private static final Logger LOGGER = LogManager.getLogger(JdbcProvider.class);

    private static final String CONNECTION_PROPS_FILE = "connection.properties";
    private static final String JDBC_DRIVER_NAME = "jdbc.driveClassName";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_PASSWORD = "jdbc.password";

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {

        LOGGER.info("init connection");
        final Properties properties = new Properties();
        final InputStream in = JdbcProvider.class.getClassLoader().getResourceAsStream(CONNECTION_PROPS_FILE);

        properties.load(in);
        String jdbcClassName = properties.getProperty(JDBC_DRIVER_NAME);
        String jdbcUrl = properties.getProperty(JDBC_URL);
        String jdbcUsername = properties.getProperty(JDBC_USERNAME);
        String jdbcPassword = properties.getProperty(JDBC_PASSWORD);
        LOGGER.info("className: {}; url: {}; userName:{}", jdbcClassName, jdbcUrl, jdbcUsername);
        Class.forName(jdbcClassName);

        return DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
    }

}
