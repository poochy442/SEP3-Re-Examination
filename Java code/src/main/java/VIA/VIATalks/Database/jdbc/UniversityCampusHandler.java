package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.University;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUniversityCampusHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UniversityCampusHandler implements IUniversityCampusHandler {
    //implementing singleton
    private static UniversityCampusHandler instance;
    private static Lock lock = new ReentrantLock();

    //private constructor for singleton implementation
    private UniversityCampusHandler() {

    }

    //getInstance method for singleton implementation
    public static UniversityCampusHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UniversityCampusHandler();
                }
            }
        }
        return instance;
    }

    public List<University> getAllUniversities() {
        List<University> universities = new ArrayList<>(); //holds universities
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        // Acquire read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.University");
            rs = statement.executeQuery();

            //go through all universities returned to result set
            while (rs.next()) {
                int id = rs.getInt("UniversityID");
                String name = rs.getString("Name");
                String country = rs.getString("Country");

                //add new university to universities list
                universities.add(new University(id,name,country));
            }
            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return universities;

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

    public List<Campus> getCampusesForUniversity(University university) {
        //checking if university is not null
        if(university != null) {
            List<Campus> campuses = new ArrayList<>(); //holds campuses
            PreparedStatement statement = null; //statement to execute db query
            ResultSet rs = null; //result set to get from executing db query

            // Acquire read from synchronization monitor
            SynchronizationMonitor.getInstance().acquireRead();

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

                statement = connection.prepareStatement("select * from dbo.Campus where UniversityID = ?");
                statement.setInt(1,university.getId());
                rs = statement.executeQuery();

                //go through all campuses returned to result set
                while (rs.next()) {
                    int id = rs.getInt("CampusID");
                    String city = rs.getString("City");
                    int postalCode = rs.getInt("PostalCode");
                    String address = rs.getString("Address");

                    //add new campus to campuses list
                    campuses.add(new Campus(id,city,postalCode,address));
                }
                // Release read from synchronization monitor
                SynchronizationMonitor.getInstance().releaseRead();

                return campuses;

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

    public boolean campusExistsOnAddress(String address) {
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        // Acquire read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("select CampusID from dbo.Campus where Address = ?");
            statement.setString(1,address);
            rs = statement.executeQuery();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            if (rs.next()) {
                int id = rs.getInt("CampusID");

                if(id>0) return true;

            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

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
}
