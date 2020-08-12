package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IRoomHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RoomHandler implements IRoomHandler {
    //implementing singleton
    private static RoomHandler instance;
    private static Lock lock = new ReentrantLock();

    //private constructor for singleton implementation
    private RoomHandler() {

    }

    //getInstance method for singleton implementation
    public static RoomHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RoomHandler();
                }
            }
        }
        return instance;
    }

    public List<Room> getRoomsForCampus(Campus campus) {
        //checking if campus in not null
        if (campus != null) {
            // Acquire read from synchronization monitor
            SynchronizationMonitor.getInstance().acquireRead();

            List<Room> rooms = new ArrayList<>(); //holds rooms
            PreparedStatement statement = null; //statement to execute db query
            ResultSet rs = null; //result set to get from executing db query

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                statement = connection.prepareStatement("select * from Room where CampusID = ?");
                statement.setInt(1, campus.getId());
                rs = statement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("RoomID");
                    int roomNumber = rs.getInt("RoomNumber");
                    String blockString = rs.getString("Block");
                    char block = blockString.charAt(0);
                    int capacity = rs.getInt("Capacity");
                    double area = rs.getDouble("Area");

                    //adding room to rooms list
                    rooms.add(new Room(id, roomNumber, block, capacity, area));
                }

                // Release read from synchronization monitor
                SynchronizationMonitor.getInstance().releaseRead();

                return rooms;

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
        return null;
    }

    public List<Room> getBookedRoomsForCampus(Campus campus) {
        List<Room> rooms = new ArrayList<>(); //holds rooms
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            // Acquire read from synchronization monitor
            SynchronizationMonitor.getInstance().acquireRead();

            statement = connection.prepareStatement("select * from Room where CampusID = ?");
            statement.setInt(1, campus.getId());
            rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("RoomID");
                int roomNumber = rs.getInt("RoomNumber");
                String blockString = rs.getString("Block");
                char block = blockString.charAt(0);
                int capacity = rs.getInt("Capacity");
                double area = rs.getDouble("Area");

                //adding room to rooms list
                rooms.add(new Room(id,roomNumber,block,capacity,area));
            }

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return rooms;

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

    public boolean attachRoomToEvent(Room room, int eventId, int numberOfSeats) {
        int roomID = 0; //holds host id
        PreparedStatement statement = null; //statement to execute db query

        //checking if capacity of the room allows to hold event with provided number of seats
        if (room.getCapacity() >= numberOfSeats) {
            // Acquire write from synchronization monitor
            SynchronizationMonitor.getInstance().acquireWrite();

            roomID = getRoomIdByBlockRoomNumber(room.getBlock(), room.getRoomNumber());

            //checking if provided room exists in db
            if (roomID > 0) {
                room.setId(roomID); //setting id found in db

                try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                    statement = connection.prepareStatement("update Event set RoomID = ? where EventID = ?");

                    statement.setInt(1, room.getId());
                    statement.setInt(2, eventId);
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        // Acquire write from synchronization monitor
                        SynchronizationMonitor.getInstance().acquireWrite();

                        return true;
                    }
                    throw new Exception("Couldn't find existing event with eventId:" + eventId);

                } catch (Exception e) {
                    e.printStackTrace();

                    // Acquire write from synchronization monitor
                    SynchronizationMonitor.getInstance().acquireWrite();

                    return false;
                } finally {
                    if (statement != null)
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }

            } else {
                try {
                    throw new Exception("Couldn't find existing room with roomNumber:" + room.getRoomNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                throw new Exception("Room capacity is smaller:" + room.getCapacity() + " then seats:" + numberOfSeats);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false; //not accessible anyway but complains of return statement
    }

    private int getRoomIdByBlockRoomNumber(char block, int roomNumber) {
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        if (Character.isLetter(block) && roomNumber > 0) {
            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                statement = connection.prepareStatement("select RoomID from Room where Block = ? and RoomNumber = ?");
                statement.setString(1, block + "");
                statement.setInt(2, roomNumber);
                rs = statement.executeQuery();

                if (rs.next()) {
                    return rs.getInt("RoomID");
                }

                return 0;

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
        } else {
            try {
                throw new Exception("block invalid:" + block + " or roomNumber:" + roomNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }


    public boolean updateRoom(Event event, int roomId) {
        //checking if event is not null
        if (event != null) {
            // Acquire write from synchronization monitor
            SynchronizationMonitor.getInstance().acquireWrite();

            PreparedStatement statement = null; //statement to execute db query

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

                statement = connection.prepareStatement("update dbo.Event set RoomID = ? where EventID = ?");
                statement.setInt(1, roomId);
                statement.setInt(2, event.getId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                } else {
                    throw new Exception("Couldn't find event with id:" + event.getId() + " or roomId:" + roomId + " is wrong");
                }

            } catch (Exception e) {
                e.printStackTrace();

                // Acquire write from synchronization monitor
                SynchronizationMonitor.getInstance().acquireWrite();

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
}
