package tek.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Map<String,Object>> getResultFromQuery(String query){
        LOGGER.debug("Executing Query {}" , query);
        try {
            ResultSet resultSet =  getConnection().createStatement().executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<Map<String,Object>> data = new ArrayList<>();
            while (resultSet.next()){
                Map<String,Object> row = new HashMap<>();
                for(int col =1; col<=metaData.getColumnCount(); col++){
                    row.put(metaData.getColumnName(col),resultSet.getObject(col));
                }
                data.add(row);
            }
            LOGGER.debug("Query result {}", data);
            return data;

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }finally {
            if(connection!=null){
//                closeDatabase();
            }

        }
    }

}
