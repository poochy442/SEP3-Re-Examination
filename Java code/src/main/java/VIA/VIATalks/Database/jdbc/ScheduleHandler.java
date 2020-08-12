package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Schedule;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IScheduleHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUniversityCampusHandler;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScheduleHandler implements IScheduleHandler {
    private UniversityCampusHandler universityCampusHandler = UniversityCampusHandler.getInstance();

    //implementing singleton
    private static ScheduleHandler instance;
    private static Lock lock = new ReentrantLock();

    //private constructor for singleton implementation
    private ScheduleHandler() {

    }

    //getInstance method for singleton implementation
    public static ScheduleHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ScheduleHandler();
                }
            }
        }
        return instance;
    }

    public boolean attachScheduleToEvent(Campus campus, int eventId) {
        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            if(universityCampusHandler.campusExistsOnAddress(campus.getAddress())) {
                // Acquire write from synchronization monitor
                SynchronizationMonitor.getInstance().acquireWrite();

                statement = connection.prepareStatement("update Event set ScheduleID = (select ScheduleID from Campus where Address = ?) where EventID = ?");
                statement.setString(1, campus.getAddress());
                statement.setInt(2, eventId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                }
                else {
                    throw new Exception("Couldn't find existing Campus address:" + campus.getAddress() + " or eventId is wrong:" + eventId);
                }
            }
            throw new Exception("Campus cannot be found with such an address:" + campus.getAddress());



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

    public boolean attachScheduleToPendingEvent(Campus campus, int eventId) {
        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            if(universityCampusHandler.campusExistsOnAddress(campus.getAddress())) {
                // Acquire write from synchronization monitor
                SynchronizationMonitor.getInstance().acquireWrite();

                statement = connection.prepareStatement("update PendingEvent set ScheduleID = (select ScheduleID from Campus where Address = ?) where PendingEventID = ?");
                statement.setString(1, campus.getAddress());
                statement.setInt(2, eventId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                }
                else {
                    throw new Exception("Couldn't find existing Campus address:" + campus.getAddress() + " or pending eventId is wrong:" + eventId);
                }
            }
            throw new Exception("Campus cannot be found with such an address:" + campus.getAddress());



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
