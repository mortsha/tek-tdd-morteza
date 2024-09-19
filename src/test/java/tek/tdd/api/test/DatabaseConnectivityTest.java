package tek.tdd.api.test;

import org.testng.annotations.Test;

import java.sql.*;

public class DatabaseConnectivityTest {

    // step 1: create connection
    String url = "jdbc:mysql://tek-database-server.mysql.database.azure.com:3306/tek_insurance_app";
    String username = "tek_student_user";
    String password = "FEB_2024";

    @Test
    public void databaseConnection() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // step 2: create a statement
            Statement statement = connection.createStatement();

            // step 3: execute query
            ResultSet resultSet = statement.executeQuery("select * from primary_person where id = 228;");

            // step 4: get result Set
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getDate("date_of_birth"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }


    }

    @Test
    public void getLatestPrimaryPersonAccount() throws SQLException {
        String query = "select * from primary_person order by id desc limit 1;";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                System.out.println("Last id from primary person: " + result.getInt("id"));
                System.out.println("Email: " + result.getString("email"));
            }

        } catch (SQLException exception) {
           throw new RuntimeException(exception.getMessage());
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
    }

    @Test
    public void getLatestPrimaryPersonId() throws SQLException {
        String query = "select max(id)  as idSelected from primary_person;";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            result.next();
            System.out.println(result.getInt("idSelected"));

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
    }

}
