package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Schedule;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IScheduleHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUniversityCampusHandler;

import java.sql.*;

public class ScheduleHandler implements IScheduleHandler {

    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";
    private IUniversityCampusHandler universityCampusHandler;

    //Constructor
    public ScheduleHandler() {
        universityCampusHandler = new UniversityCampusHandler();
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public boolean attachScheduleToEvent(Campus campus, int eventId) {
        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = getConnectionToDB()) {

            if(universityCampusHandler.campusExistsOnAddress(campus.getAddress())) {
                statement = connection.prepareStatement("update Event set ScheduleID = (select ScheduleID from Campus where Address = ?) where EventID = ?");
                statement.setString(1, campus.getAddress());
                statement.setInt(2, eventId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
                else {
                    throw new Exception("Couldn't find existing Campus address:" + campus.getAddress() + " or eventId is wrong:" + eventId);
                }
            }
            throw new Exception("Campus cannot be found with such an address:" + campus.getAddress());



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public boolean attachScheduleToPendingEvent(Campus campus, int eventId) {
        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = getConnectionToDB()) {

            if(universityCampusHandler.campusExistsOnAddress(campus.getAddress())) {
                statement = connection.prepareStatement("update PendingEvent set ScheduleID = (select ScheduleID from Campus where Address = ?) where PendingEventID = ?");
                statement.setString(1, campus.getAddress());
                statement.setInt(2, eventId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
                else {
                    throw new Exception("Couldn't find existing Campus address:" + campus.getAddress() + " or pending eventId is wrong:" + eventId);
                }
            }
            throw new Exception("Campus cannot be found with such an address:" + campus.getAddress());



        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
