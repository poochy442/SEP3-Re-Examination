package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.ITicketHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketHandler implements ITicketHandler {
    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-AJ2N3MPF\\Kenne:1433;databaseName=VIATalks;user=VIATalks_Admin;password=Password";

    //Constructor
    public TicketHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    /*public List<Integer> getTicketsCountForEvents(List<Event> events) {
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
    }*/

    public boolean issueTicketForEvent(Event event, int userId) {
        //checking if event is not null, its id and userId valid
        if (event != null && event.getId() > 0 && userId > 0) {
            PreparedStatement statement = null; //statement to execute db query
            ResultSet rs = null; //result set to get from executing db query

            try (Connection connection = getConnectionToDB()) {

                statement = connection.prepareStatement("select ScheduleID from Event where EventID = ?");
                statement.setInt(1, event.getId());
                rs = statement.executeQuery();

                //checking if the result set is not empty
                if (rs.next()) {
                    int scheduleId = rs.getInt("ScheduleID");

                    //checking if the ScheduleID is valid
                    if (scheduleId > 0) {
                        statement = connection.prepareStatement("insert into Ticket(TicketNumber,EventID,UserID) values(?,?,?)",
                                new String[]{"TicketID"});
                        String roomNumber = Integer.toString(scheduleId) + "_" + Integer.toString(event.getId()) + "_" + Integer.toString(userId);
                        statement.setString(1, roomNumber);
                        statement.setInt(2, event.getId());
                        statement.setInt(3, userId);
                        statement.executeUpdate();
                        rs = statement.getGeneratedKeys();

                        if (rs.next()) {
                            return true;
                        } else {
                            throw new Exception("Something went wrong while creating ticket for scheduleId:" +
                                    scheduleId + " ,eventId:" + event.getId() + " ,userId:" + userId);
                        }
                    } else {
                        throw new Exception("Wrong ScheduleID inside event with iD:" + event.getId());
                    }
                } else {
                    throw new Exception("Couldnt get the ScheduleID fro evntID:" + event.getId());
                }


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
                if (rs != null)
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
        return false;
    }
}
