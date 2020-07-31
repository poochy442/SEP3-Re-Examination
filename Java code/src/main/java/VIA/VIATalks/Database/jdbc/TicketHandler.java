package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketHandler {
    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    //Constructor
    public TicketHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public List<Integer> getTicketsCountForEvents(List<Event> events) {
        List<Integer> ticketsCount = new ArrayList<>(); //holds ticket count for events
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try(Connection connection = getConnectionToDB()) {
            statement = connection.prepareStatement("select count(TicketID) from dbo.Ticket where EventID = ?");

            //go through all events ids and count tickets for each event
            for (int i=0;i<events.size();i++) {
                statement.setInt(1,events.get(i).getId());
                rs = statement.executeQuery();
                if (rs.next()) {
                    ticketsCount.add(rs.getInt(1));
                }
                else {
                    ticketsCount.add(0);
                }
            }

            return ticketsCount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        finally {
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
