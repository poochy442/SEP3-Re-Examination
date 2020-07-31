package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IRoomHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomHandler implements IRoomHandler {

    //connection string to db
    private final String dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";

    //Constructor
    public RoomHandler() {
    }

    //Establish connection to db and return it
    private Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }


    public boolean attachRoomToEvent(Room room, int eventId) {
        int roomID = 0; //holds host id
        PreparedStatement statement = null; //statement to execute db query

        roomID = getRoomIdByRoomNumber(room.getRoomNumber());

        //checking if provided room exists in db
        if (roomID > 0) {
            room.setId(roomID); //setting id found in db

            try (Connection connection = getConnectionToDB()) {
                statement = connection.prepareStatement("update Event set RoomID = ? where EventID = ?");

                statement.setInt(1, room.getId());
                statement.setInt(2, eventId);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
                throw new Exception("Couldn't find existing event with eventId:" + eventId);

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

        try {
            throw new Exception("Couldn't find existing room with roomNumber:" + room.getRoomNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getRoomIdByRoomNumber(int roomNumber) {
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
            statement = connection.prepareStatement("select RoomID from Room where RoomNumber = ?");
            statement.setInt(1, roomNumber);
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
    }

    public List<Room> getRoomsForCampus(Campus campus) {
        List<Room> rooms = new ArrayList<>(); //holds rooms
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = getConnectionToDB()) {
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

            return rooms;

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

    public boolean updateRoom(Event event, int roomId) {
            PreparedStatement statement = null; //statement to execute db query

            try (Connection connection = getConnectionToDB()) {

                statement = connection.prepareStatement("update dbo.Event set RoomID = ? where EventID = ?");
                statement.setInt(1, roomId);
                statement.setInt(2, event.getId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
                else {
                    throw new Exception("Couldn't find event with id:" + event.getId() + " or roomId:" + roomId + " is wrong");
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
