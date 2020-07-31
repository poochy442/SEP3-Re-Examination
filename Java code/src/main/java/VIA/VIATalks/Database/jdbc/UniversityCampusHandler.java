package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.University;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUniversityCampusHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UniversityCampusHandler implements IUniversityCampusHandler {

    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";


    //Constructor
    public UniversityCampusHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public List<University> getAllUniversities() {
        List<University> universities = new ArrayList<>(); //holds universities
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.University");
            rs = statement.executeQuery();

            //go through all universities returned to result set
            while (rs.next()) {
                int id = rs.getInt("UniversityID");
                String name = rs.getString("Name");
                String country = rs.getString("Country");

                //add new university to universities list
                universities.add(new University(id,name,country));
            }
            return universities;

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

    public List<Campus> getCampusesForUniversity(University university) {
        List<Campus> campuses = new ArrayList<>(); //holds campuses
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.Campus where UniversityID = ?");
            statement.setInt(1,university.getId());
            rs = statement.executeQuery();

            //go through all campuses returned to result set
            while (rs.next()) {
                int id = rs.getInt("CampusID");
                String city = rs.getString("City");
                int postalCode = rs.getInt("PostalCode");
                String address = rs.getString("Address");

                //add new campus to campuses list
                campuses.add(new Campus(id,city,postalCode,address));
            }
            return campuses;

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
}
