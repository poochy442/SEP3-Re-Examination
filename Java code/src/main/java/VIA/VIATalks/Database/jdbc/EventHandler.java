package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventHandler implements IEventHandler {
    //connection string to db
   //private final String dbConnectionString = "jdbc:sqlserver://DESKTOP-ADI2GPO\\Bruger:1433;databaseName=VIATalks;";


    private EventCategoryHandler eventCategoryHandler = EventCategoryHandler.getInstance();
    private HostHandler hostHandler = HostHandler.getInstance();
    private RoomHandler roomHandler = RoomHandler.getInstance();
    private ScheduleHandler scheduleHandler = ScheduleHandler.getInstance();
    private TicketHandler ticketHandler = TicketHandler.getInstance();

    //implementing singleton
    private static EventHandler instance;
    private static Lock lock = new ReentrantLock();

    //private constructor for singleton implementation
    private EventHandler() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> events = getUpcomingEvents(now);
        if(events.size() < 1){
            createEvent(new Event(
                    1,
                    "Tuition",
                    "How to be cool",
                    now.plusHours(36),
                    now.plusHours(38),
                    30,
                    25,
                    new Host(
                            1,
                            "Kenneth",
                            "Jensen",
                            "email",
                            "22667902"),
                    new Room(
                            1,
                            103,
                            'C',
                            30,
                            45),
                    new Campus(
                            1,
                            "Horsens",
                            8700,
                            "Chr M Østergaards Vej 4")));
        }
    }

    //getInstance method for singleton implementation
    public static EventHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new EventHandler();
                }
            }
        }
        return instance;
    }


    public List<Event> getUpcomingEvents(LocalDateTime date) {
        List<Event> events = new ArrayList<>(); //holds events
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        //checking date is not null
        if(date != null) {
            // Acquire read from synchronization monitor
            SynchronizationMonitor.getInstance().acquireRead();

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                statement = connection.prepareStatement("select e.EventID, e.EventName, e.StartDate, e.EndDate, e.NumberOfSeats, ec.Name, \n" +
                        "  h.HostID, h.FirstName, h.LastName, h.Email, h.Telephone,\n" +
                        "  r.RoomID, r.RoomNumber, r.Block, r.Capacity, r.Area,\n" +
                        "  c.CampusID, c.City, c.PostalCode, c.Address,\n" +
                        "  (select count(TicketID) as Tickets from Ticket t where t.EventID = e.EventID)\n" +
                        "  from\n" +
                        "  Event e join EventCategory ec on e.EventCategoryID = ec.EventCategoryID\n" +
                        "                 join Host h on e.HostID = h.HostID\n" +
                        "\t\t\t\t join Room r on e.RoomID = r.RoomID\n" +
                        "\t\t\t\t join Campus c on e.ScheduleID = c.ScheduleID\n" +
                        "where e.StartDate > ?");
                statement.setString(1, date.format(mssqlDateFormat));
                rs = statement.executeQuery();

                //go through all events returned to result set
                //set all event properties as well as host,room and campus instances
                while (rs.next()) {
                    int eventId = rs.getInt("EventID");
                    String category = rs.getString("Name");
                    String eventName = rs.getString("EventName");
                    LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                            rs.getTime("StartDate").toLocalTime());
                    LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                            rs.getTime("EndDate").toLocalTime());
                    int seats = rs.getInt("NumberOfSeats");

                    int hostId = rs.getInt("HostID");
                    String hostFName = rs.getString("FirstName");
                    String hostLName = rs.getString("LastName");
                    String hostEmail = rs.getString("Email");
                    String hostTelephone = rs.getString("Telephone");

                    int roomId = rs.getInt("RoomID");
                    int roomNumber = rs.getInt("RoomNumber");
                    char roomBlock = rs.getString("Block").charAt(0);
                    int roomCapacity = rs.getInt("Capacity");
                    double roomArea = rs.getDouble("Area");

                    int campusId = rs.getInt("CampusID");
                    String campusCity = rs.getString("City");
                    int postalCode = rs.getInt("PostalCode");
                    String campusAddress = rs.getString("Address");

                    Event event = new Event(eventId, category, eventName, startDate, endDate, seats);
                    event.setHost(new Host(hostId, hostFName, hostLName, hostEmail, hostTelephone));
                    event.setRoom(new Room(roomId, roomNumber, roomBlock, roomCapacity, roomArea));
                    event.setCampus(new Campus(campusId, campusCity, postalCode, campusAddress));

                    //add new event to events list
                    events.add(event);

                }
                // Release read from synchronization monitor
                SynchronizationMonitor.getInstance().releaseRead();

                return events;

            } catch (Exception e) {
                e.printStackTrace();

                // Release read from synchronization monitor
                SynchronizationMonitor.getInstance().releaseRead();

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
        return events;


    }

    public List<Event> getAllRequestedEvents() {
        // Acquire read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        List<Event> events = new ArrayList<>(); //holds events
        List<Integer> categoryIds = new ArrayList<>(); //holds event category ids
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement(" select pe.PendingEventID, pe.EventName, pe.StartDate, pe.EndDate, pe.NumberOfSeats, ec.Name, " +
                    "h.HostID, h.FirstName, h.LastName, h.Email, h.Telephone,\n" +
                    " c.CampusID, c.City, c.PostalCode, c.Address from\n" +
                    " PendingEvent pe join EventCategory ec on pe.EventCategoryID = ec.EventCategoryID\n" +
                    "                 join Host h on pe.HostID = h.HostID\n" +
                    "\t\t\t\t join Campus c on pe.ScheduleID = c.ScheduleID");
            rs = statement.executeQuery();

            //go through all pending events returned to result set
            while (rs.next()) {
                int eventId = rs.getInt("PendingEventID");
                String eventName = rs.getString("EventName");
                LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartDate").toLocalTime());
                LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndDate").toLocalTime());
                int seats = rs.getInt("NumberOfSeats");

                String category = rs.getString("Name");

                int hostId = rs.getInt("HostID");
                String hostFName = rs.getString("FirstName");
                String hostLName = rs.getString("LastName");
                String hostEmail = rs.getString("Email");
                String hostTelephone = rs.getString("Telephone");

                int campusId = rs.getInt("CampusID");
                String campusCity = rs.getString("City");
                int postalCode = rs.getInt("PostalCode");
                String campusAddress = rs.getString("Address");

                Event event = new Event(eventId, category, eventName, startDate, endDate, seats);
                event.setHost(new Host(hostId, hostFName, hostLName, hostEmail, hostTelephone));
                event.setCampus(new Campus(campusId, campusCity, postalCode, campusAddress));

                //add new event to events list
                events.add(event);
            }
            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return events;

        } catch (Exception e) {
            e.printStackTrace();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

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
        // Acquire read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        List<Event> events = new ArrayList<>(); //holds events
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            statement = connection.prepareStatement("select e.EventID, e.EventName, e.StartDate, e.EndDate, e.NumberOfSeats, ec.Name,\n" +
                    "\t   r.RoomID, r.RoomNumber, r.Block, r.Capacity, r.Area\n" +
                    "from EventCategory ec join Event e on ec.EventCategoryID = e.EventCategoryID\n" +
                    "                      join  Room r on r.RoomID = e.RoomID\n" +
                    "where e.ScheduleID = ? and e.StartDate > ? \n" +
                    "order by Block,RoomNumber,StartDate,EndDate");
            statement.setInt(1, scheduleID);
            statement.setString(2, date.format(mssqlDateFormat));
            rs = statement.executeQuery();

            //go through all events returned to result set
            //setting all event properties as well as room instance
            while (rs.next()) {
                int eventId = rs.getInt("EventID");
                String category = rs.getString("Name");
                String eventName = rs.getString("EventName");
                LocalDateTime startDate = LocalDateTime.of(rs.getDate("StartDate").toLocalDate(),
                        rs.getTime("StartDate").toLocalTime());
                LocalDateTime endDate = LocalDateTime.of(rs.getDate("EndDate").toLocalDate(),
                        rs.getTime("EndDate").toLocalTime());
                int seats = rs.getInt("NumberOfSeats");


                int roomId = rs.getInt("RoomID");
                int roomNumber = rs.getInt("RoomNumber");
                String blockString = rs.getString("Block");
                char block = blockString.charAt(0);
                int capacity = rs.getInt("Capacity");
                double area = rs.getDouble("Area");

                Event event = new Event(eventId, category, eventName, startDate, endDate, seats);
                event.setRoom(new Room(roomId, roomNumber, block, capacity, area));

                //add new event to events list
                events.add(event);
            }
            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return events;

        } catch (Exception e) {
            e.printStackTrace();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

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
        // Acquire writefrom synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        int eventID = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            //checking if event category and event name and start date and host, room, schedule are valid
            if ((event.getEventCategory() != null && event.getEventCategory().length() > 0) &&
                    (event.getEventName() != null && event.getEventName().length() > 0) &&
                    (event.getStartDate() != null && event.getStartDate().isAfter(LocalDateTime.now())) &&
                    event.getHost() != null && event.getRoom() != null && event.getCampus() != null) {

                statement = connection.prepareStatement("insert into dbo.Event(EventName ,StartDate ,EndDate ,NumberOfSeats) " +
                        "values(?,?,?,?)", new String[]{"EventID"}); //setting that jdbc returns values of column EventID
                statement.setString(1, event.getEventName());
                statement.setString(2, event.getStartDate().format(mssqlDateFormat));
                statement.setString(3, event.getEndDate().format(mssqlDateFormat));
                statement.setInt(4, event.getNumberOfSeats());

                statement.executeUpdate();
                rs = statement.getGeneratedKeys();

                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                if (rs.next()) {
                    eventID = rs.getInt(1);
                } else {
                    return false;
                }

                if (!eventCategoryHandler.attachCategoryToEvent(event.getEventCategory(), eventID)) {
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
                        " or event start date not valid:" + event.getStartDate() +
                        " or host/room/campus is null");
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Release write from synchronization monitor
            SynchronizationMonitor.getInstance().releaseWrite();

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

    public boolean requestEvent(Event event) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        int eventID = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            //checking if event category, event name, start date and host, campus are valid
            if ((event.getEventCategory() != null && event.getEventCategory().length() > 0) &&
                    (event.getEventName() != null && event.getEventName().length() > 0) &&
                    (event.getStartDate() != null && event.getStartDate().isAfter(LocalDateTime.now())) &&
                    event.getHost() != null && event.getCampus() != null) {

                statement = connection.prepareStatement("insert into dbo.PendingEvent(EventName ,StartDate ,EndDate ,NumberOfSeats) " +
                        "values(?,?,?,?)", new String[]{"EventID"}); //setting that jdbc returns values of column EventID
                statement.setString(1, event.getEventName());
                statement.setString(2, event.getStartDate().format(mssqlDateFormat));
                statement.setString(3, event.getEndDate().format(mssqlDateFormat));
                statement.setInt(4, event.getNumberOfSeats());

                statement.executeUpdate();
                rs = statement.getGeneratedKeys();

                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                if (rs.next()) {
                    eventID = rs.getInt(1);
                } else {
                    return false;
                }

                if (!eventCategoryHandler.attachCategoryToPendingEvent(event.getEventCategory(), eventID)) {
                    deletePendingEvent(eventID);
                    return false;
                }


                if (!hostHandler.attachHostToPendingEvent(event.getHost(), eventID)) {
                    deletePendingEvent(eventID);
                    return false;
                }


                if (!scheduleHandler.attachScheduleToPendingEvent(event.getCampus(), eventID)) {
                    deletePendingEvent(eventID);
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

            // Release write from synchronization monitor
            SynchronizationMonitor.getInstance().releaseWrite();

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

        //checking if event is not null
        if(event != null) {
            // Acquire write from synchronization monitor
            SynchronizationMonitor.getInstance().acquireWrite();

            PreparedStatement statement = null;

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                DateTimeFormatter mssqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                statement = connection.prepareStatement("update dbo.Event set EventName = ?,StartDate = ?, EndDate = ?, NumberOfSeats = ? " +
                        "where EventID = ?");
                statement.setString(1, event.getEventName());
                statement.setString(2, event.getStartDate().format(mssqlDateFormat));
                statement.setString(3, event.getEndDate().format(mssqlDateFormat));
                statement.setInt(4, event.getNumberOfSeats());
                statement.setInt(5, event.getId());

                int rowsAffected = statement.executeUpdate();

                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                if (rowsAffected > 0) {
                    return true;
                }
                return false;

            } catch (Exception e) {
                e.printStackTrace();

                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

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
        return false;

    }

    public boolean deleteEvent(int id) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null;

        //deleting tickets stored for the vent in the db
        ticketHandler.deleteTicketsForEvent(id);

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("delete from dbo.Event where EventID = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                return true;
            } else {
                throw new Exception("No such event with id:" + id);
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Release write from synchronization monitor
            SynchronizationMonitor.getInstance().releaseWrite();

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

    public boolean deletePendingEvent(int id) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("delete from dbo.PendingEvent where PendingEventID = ?");
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                return true;
            } else {
                throw new Exception("No such pending event with id:" + id);
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Release write from synchronization monitor
            SynchronizationMonitor.getInstance().releaseWrite();

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
