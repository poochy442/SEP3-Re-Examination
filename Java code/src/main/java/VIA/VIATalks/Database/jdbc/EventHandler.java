package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventHandler implements IEventHandler {
    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    private TicketHandler ticketHandler;
    private IEventCategoryHandler eventCategoryHandler;
    private IHostHandler hostHandler;
    private IRoomHandler roomHandler;
    private IScheduleHandler scheduleHandler;

    //Constructor
    public EventHandler() {
        ticketHandler = new TicketHandler(); //!! make singleton for handlers !!
        eventCategoryHandler = new EventCategoryHandler(); //!! make singleton for handlers !!
        hostHandler = new HostHandler(); //!! make singleton for handlers !!
        roomHandler = new RoomHandler(); //!! make singleton for handlers !!
        scheduleHandler = new ScheduleHandler(); //!! make singleton for handlers !!
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }

    public List<Event> getUpcomingEvents(LocalDateTime date) {
        List<Event> events = new ArrayList<>(); //holds events
        List<Integer> categoryIds = new ArrayList<>(); //holds event category ids
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            statement = connection.prepareStatement("select * from dbo.Event where StartDate > ?");
            statement.setString(1, date.format(mssqlDateFormat));
            rs = statement.executeQuery();

            //go through all events returned to result set
            while (rs.next()) {
                int id = rs.getInt("EventID");
                String name = rs.getString("EventName");
                LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartDate").toLocalTime());
                LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndDate").toLocalTime());
                int seats = rs.getInt("NumberOfSeats");

                //collecting event categories ids to find and map later event categories names to events
                categoryIds.add(rs.getInt("EventCategoryID"));

                //add new event to events list
                events.add(new Event(id, null, name, startDate, endDate, seats));

            }

            //getting event categories names
            List<String> eventCategoryNames = eventCategoryHandler.getEventCategoriesById(categoryIds);

            //mapping categories names to events
            if (eventCategoryNames != null && eventCategoryNames.size() > 0) {
                for (int i = 0; i < events.size(); i++) {
                    events.get(i).setEventCategory(eventCategoryNames.get(i));
                }
            } else {
                throw new Exception("Event category names list is null/size of 0");
            }


            //getting ticket counts(registered users) for events
            List<Integer> ticketCounts = ticketHandler.getTicketsCountForEvents(events);

            //setting registered users for events
            for (int i = 0; i < events.size(); i++) {
                events.get(i).setRegisteredUsers(ticketCounts.get(i));
            }


            return events;

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

    public List<Event> getEventsBookedInRooms(int scheduleID, LocalDateTime date) {
        List<Event> events = new ArrayList<>(); //holds events
        List<Integer> categoryIds = new ArrayList<>(); //holds event categoru ids
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            statement = connection.prepareStatement("select * from\n" +
                    "  Room r join Event e on r.RoomID = e.RoomID\n" +
                    "  where e.ScheduleID = ? and e.StartDate > ?\n" +
                    "  order by Block,RoomNumber,StartDate,EndDate");
            statement.setInt(1,scheduleID);
            statement.setString(2, date.format(mssqlDateFormat));
            rs = statement.executeQuery();

            //go through all events returned to result set
            while (rs.next()) {
                int eventId = rs.getInt("EventID");
                String eventName = rs.getString("EventName");
                LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartDate").toLocalTime());
                LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndDate").toLocalTime());
                int seats = rs.getInt("NumberOfSeats");

                //collecting event categories ids to find and map later event categories names to events
                categoryIds.add(rs.getInt("EventCategoryID"));

                int roomId = rs.getInt("RoomID");
                int roomNumber = rs.getInt("RoomNumber");
                String blockString = rs.getString("Block");
                char block = blockString.charAt(0);
                int capacity = rs.getInt("Capacity");
                double area = rs.getDouble("Area");

                Event event = new Event(eventId,null,eventName,startDate,endDate,seats);
                event.setRoom(new Room(roomId,roomNumber,block,capacity,area));

                //add new event to events list
                events.add(event);

            }

            //getting event categories names
            List<String> eventCategoryNames;

            if(categoryIds.size()>0) {
                eventCategoryNames = eventCategoryHandler.getEventCategoriesById(categoryIds);

                //mapping categories names to events
                if (eventCategoryNames != null && eventCategoryNames.size() > 0) {
                    for (int i = 0; i < events.size(); i++) {
                        events.get(i).setEventCategory(eventCategoryNames.get(i));
                    }
                } else {
                    throw new Exception("Event category names list is null/size of 0");
                }
            }

            return events;

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

    public boolean createEvent(Event event) {
        int eventID = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            //checking if event category and event name and start date are valid
            if ((event.getEventCategory() != null && event.getEventCategory().length() > 0) &&
                    (event.getEventName() != null && event.getEventName().length() > 0) &&
                    (event.getStartDate() != null && event.getStartDate().isAfter(LocalDateTime.now()))) {

                statement = connection.prepareStatement("insert into dbo.Event(EventName ,StartDate ,EndDate ,NumberOfSeats) " +
                        "values(?,?,?,?)", new String[]{"EventID"}); //setting that jdbc returns values of column EventID
                statement.setString(1, event.getEventName());
                statement.setString(2, event.getStartDate().format(mssqlDateFormat));
                statement.setString(3, event.getEndDate().format(mssqlDateFormat));
                statement.setInt(4, event.getNumberOfSeats());

                statement.executeUpdate();
                rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    eventID = rs.getInt(1);
                } else {
                    return false;
                }

                if(!eventCategoryHandler.attachCategoryToEvent(event.getEventCategory(),eventID)) {
                    deleteEvent(eventID);
                    return false;
                }


                if (!hostHandler.attachHostToEvent(event.getHost(), eventID)) {
                    deleteEvent(eventID);
                    return false;
                }

                if (!roomHandler.attachRoomToEvent(event.getRoom(), eventID, event.getNumberOfSeats())) {
                    deleteEvent(eventID);
                    return false;
                }

                if (!scheduleHandler.attachScheduleToEvent(event.getCampus(), eventID)) {
                    deleteEvent(eventID);
                    return false;
                }
                return true;

            } else {
                throw new Exception("Event category not valid:" + event.getEventCategory() +
                        " or event name not valid:" + event.getEventName() +
                        " or event start date not valid:" + event.getStartDate());
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
            } else {
                throw new Exception("No such event with id:" + id);
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
        }
    }

}
