package VIA.VIATalks.Database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCategoryHandler {
    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    //Constructor
    public EventCategoryHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public List<String> getEventCategoriesById(List<Integer> categoryIds) {
        List<String> categories = new ArrayList<>();  //holds event categories names
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
            statement = connection.prepareStatement("select Name from dbo.EventCategory where EventCategoryID = ?");

            //go through all categories and et the name
            for (int i = 0; i < categoryIds.size(); i++) {
                statement.setInt(1, categoryIds.get(i));
                rs = statement.executeQuery();
                if (rs.next()) {
                    categories.add(rs.getString("Name"));
                } else {
                    throw new Exception("Event Category Id NOT in Database" + categoryIds.get(i));
                }
            }
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public int getEventCategoryIdByName(String category) {
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
            statement = connection.prepareStatement("select EventCategoryID from dbo.EventCategory where Name = ?");

            statement.setString(1, category);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("EventCategoryID");
            }

            throw new Exception("Event Category Id not found for category:" + category);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
