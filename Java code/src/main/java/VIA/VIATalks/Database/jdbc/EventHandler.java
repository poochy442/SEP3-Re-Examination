package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    public EventHandler() {
    }

    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.Event");
            rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("EventID");
                String name = rs.getString("EventName");
                LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartDate").toLocalTime());
                LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndDate").toLocalTime());
                int seats = rs.getInt("NumberOfSeats");
                int eventCategoryID = rs.getInt("EventCategoryID");
                int hostID = rs.getInt("HostID");
                int roomID = rs.getInt("RoomID");

                events.add(new Event(id, null, null, null,
                        name, null, null, null, null,
                        startDate, endDate, '_', -1, seats));
            }


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
        return events;
    }

    public boolean createEvent(Event event) {
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            statement = connection.prepareStatement("insert into dbo.Event(EventName ,StartDate ,EndDate ,NumberOfSeats ) values(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getStartDate().format(mssqlDateFormat));
            statement.setString(3, event.getEndDate().format(mssqlDateFormat));
            statement.setInt(4, event.getNumberOfSeats());

            statement.executeUpdate();

            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return true;
            }
            return false;

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

    public boolean updateEvent(Event event) {
        PreparedStatement statement = null;

        try (Connection connection = getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            statement = connection.prepareStatement("update dbo.Event set EventName = ?,StartDate = ?, EndDate = ?, NumberOfSeats = ? " +
                    "where EventID = ?");
            statement.setString(1, event.getEventName());
            statement.setString(2, event.getStartDate().format(mssqlDateFormat));
            statement.setString(3, event.getEndDate().format(mssqlDateFormat));
            statement.setInt(4, event.getNumberOfSeats());
            statement.setInt(5, event.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            return false;

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

    public boolean deleteEvent(int id) {
        PreparedStatement statement = null;

        try (Connection connection = getConnectionToDB()) {

            statement = connection.prepareStatement("delete from dbo.Event where EventID = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }


}
