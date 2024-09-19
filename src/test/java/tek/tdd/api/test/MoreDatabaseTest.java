package tek.tdd.api.test;

import org.testng.annotations.Test;
import tek.tdd.api.models.response.AccountResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreDatabaseTest extends ApiTestsBase {

    @Test
    public void convertResultSetToMap() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select * from primary_person order by id desc limit 5;";
        ResultSet result =  dbUtility.executeQuery(query);

        ResultSetMetaData metaData = result.getMetaData();
        List<Map<String,Object>> data = new ArrayList<>();

        while (result.next()){
            Map<String,Object> row = new HashMap<>();
            for(int col = 1; col<=metaData.getColumnCount(); col++){
                row.put(metaData.getColumnName(col),result.getObject(col));

            }
            data.add(row);
        }

        System.out.println(data);
    }

    @Test
    public void convertResultSetToObject() throws SQLException {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select * from primary_person order by id desc limit 5;";
        ResultSet result = dbUtility.executeQuery(query);

        ResultSetMetaData metaData = result.getMetaData();
        List<AccountResponse> data = new ArrayList<>();
        while(result.next()){
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(result.getInt("id"));
            accountResponse.setEmail(result.getString("email"));
            accountResponse.setFirstName(result.getString("first_name"));

            data.add(accountResponse);
        }
        System.out.println(data);
    }
}
