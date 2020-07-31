package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScheduleHandler {

    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    //Constructor
    public ScheduleHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public boolean attachScheduleToEvent(int eventId) {
        return false;
    }
}
