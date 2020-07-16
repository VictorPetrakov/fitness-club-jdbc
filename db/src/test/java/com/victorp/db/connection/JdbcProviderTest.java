package com.victorp.db.connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcProviderTest {
    private static final Logger LOGGER = LogManager.getLogger(JdbcProviderTest.class);

   @Test
    public void test() throws Exception{
       final Connection connection = JdbcProvider.getConnection();
       Assert.assertNotNull(connection);
       connection.close();

    }

    @Test
    public void testMetadata() throws Exception{
        try(final Connection connection = JdbcProvider.getConnection()){
            final DatabaseMetaData metaData = connection.getMetaData();
            LOGGER.info("Url: {}",metaData.getURL());
            LOGGER.info("username: {}",metaData.getUserName());
            LOGGER.info("DB name: {}-{}",metaData.getDatabaseProductName(),metaData.getDatabaseProductVersion());
        }
    }
    @Test
    public void testStatement() throws Exception{
       try(final Connection connection = JdbcProvider.getConnection()){
           try(final Statement statement = connection.createStatement()){
               try(final ResultSet resultSet = statement.executeQuery("SELECT * FROM fitness_club_db.clients ")){
                   while (resultSet.next()){
                       LOGGER.info("Clients: {} {} {}",
                               resultSet.getInt(1),
                               resultSet.getString(2),
                               resultSet.getString(3)

                       );
                   }
               }

           }
       }

    }

}
