package tek.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class DatabaseUtility extends SeleniumUtility {

    private static Connection connection;
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public Connection getConnection() {
        if (connection == null) {
            try {
                String url = getProperty("db.url");
                String username = getProperty("db.username");
                String password = getProperty("db.password");
                connection = DriverManager.getConnection(url, username, password);
                LOGGER.debug("Connected to the database successfully");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to the database. " + e.getMessage());
            }
        }
        return connection;
    }

    public ResultSet executeQuery(String query) {
        try {
            LOGGER.debug("Executing the query");
            return getConnection().createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute the query. " + e.getMessage());
        }
    }


    public void closeDatabase() {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.debug("Connection closed successfully");
            } catch (SQLException exception) {
                LOGGER.debug("Failed to close connection {} ", exception.getMessage());
                throw new RuntimeException("Failed to close connection:  " + exception.getMessage());
            }
        }

    }

}
