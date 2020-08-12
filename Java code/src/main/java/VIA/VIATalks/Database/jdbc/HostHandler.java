package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IHostHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HostHandler implements IHostHandler {
    //implementing singleton
    private static HostHandler instance;
    private static Lock lock = new ReentrantLock();

    //private constructor for singleton implementation
    private HostHandler() {

    }

    //getInstance method for singleton implementation
    public static HostHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new HostHandler();
                }
            }
        }
        return instance;
    }

    public List<Host> getAllHosts() {
        // Acquire readfrom synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        List<Host> hosts = new ArrayList<>(); //holds hosts
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.Host");
            rs = statement.executeQuery();

            //go through all hosts returned to result set
            while (rs.next()) {
                int id = rs.getInt("HostID");
                String fname = rs.getString("FirstName");
                String lname = rs.getString("LastName");
                String email = rs.getString("Email");
                String telephone = rs.getString("Telephone");

                //add new host to universities list
                hosts.add(new Host(id,fname,lname,email,telephone));
            }
            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return hosts;

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

    public boolean attachHostToEvent(Host host, int eventId) {
        int hostID = 0; //holds host id
        boolean result = false; //holds return of the method

        hostID = getHostIdByEmail(host.getHostEmail());

        //checking if provided host exists in db
        if (hostID > 0) {
            host.setId(hostID); //setting id found in db
            result = attachExistingHostToEvent(host, eventId);
            if (result) {
                return true;
            }
            return false;
        } else {
            return attachNewHostToEvent(host, eventId);
        }

    }

    public boolean attachHostToPendingEvent(Host host, int eventId) {
        int hostID = 0; //holds host id
        boolean result = false; //holds return of the method

        hostID = getHostIdByEmail(host.getHostEmail());

        //checking if provided host exists in db
        if (hostID > 0) {
            host.setId(hostID); //setting id found in db
            result = attachExistingHostToPendingEvent(host, eventId);
            if (result) {
                return true;
            }
            return false;
        } else {
            return attachNewHostToPendingEvent(host, eventId);
        }
    }

    private int getHostIdByEmail(String email) {
        // Access read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            statement = connection.prepareStatement("select HostID from Host where Email = ?");
            statement.setString(1, email);
            rs = statement.executeQuery();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            if (rs.next()) {
                return rs.getInt("HostID");
            }

            return 0;

        } catch (Exception e) {
            e.printStackTrace();

            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

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

    private boolean attachExistingHostToEvent(Host host, int eventId) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            statement = connection.prepareStatement("update Event set HostID = ? where EventID = ?");

            statement.setInt(1, host.getId());
            statement.setInt(2, eventId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                return true;
            }
            throw new Exception("Couldn't find existing host with id:" + host.getId() + " or eventId is wrong:" + eventId);

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

    private boolean attachExistingHostToPendingEvent(Host host, int eventId) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null; //statement to execute db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            statement = connection.prepareStatement("update PendingEvent set HostID = ? where PendingEventID = ?");

            statement.setInt(1, host.getId());
            statement.setInt(2, eventId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Release write from synchronization monitor
                SynchronizationMonitor.getInstance().releaseWrite();

                return true;
            }
            throw new Exception("Couldn't find existing host with id:" + host.getId() + " or pending eventId is wrong:" + eventId);

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

    private boolean attachNewHostToEvent(Host host, int eventId) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            //checking if host names and email are valid
            if ((host.getHostFirstName() != null && host.getHostFirstName().length() > 0) &&
                    (host.getHostLastName() != null && host.getHostLastName().length() > 0) &&
                    (host.getHostEmail() != null && host.getHostEmail().length() > 0)) {

                statement = connection.prepareStatement("insert into Host(FirstName,LastName,Email,Telephone) " +
                        "values(?,?,?,?)", new String[]{"HostID"}); //setting that jdbc returns values of column HostID
                statement.setString(1, host.getHostFirstName());
                statement.setString(2, host.getHostLastName());
                statement.setString(3, host.getHostEmail());
                statement.setString(4, host.getHostTelephone());

                statement.executeUpdate();
                rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    host.setId(rs.getInt(1));
                    statement = connection.prepareStatement("update Event set HostID = ? where EventID = ?");

                    statement.setInt(1, host.getId());
                    statement.setInt(2, eventId);
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Release write from synchronization monitor
                        SynchronizationMonitor.getInstance().releaseWrite();

                        return true;
                    }
                    throw new Exception("Couldn't find existing host with id:" + host.getId() + " or eventId is wrong:" + eventId);
                }
                throw new Exception("Couldnt create new host");
            } else {
                throw new Exception("host name wrong:" + host.getHostFirstName() + " " + host.getHostLastName() +
                        " or email:" + host.getHostEmail());
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

    private boolean attachNewHostToPendingEvent(Host host, int eventId) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            //checking if host names and email are valid
            if ((host.getHostFirstName() != null && host.getHostFirstName().length() > 0) &&
                    (host.getHostLastName() != null && host.getHostLastName().length() > 0) &&
                    (host.getHostEmail() != null && host.getHostEmail().length() > 0)) {

                statement = connection.prepareStatement("insert into Host(FirstName,LastName,Email,Telephone) " +
                        "values(?,?,?,?)", new String[]{"HostID"}); //setting that jdbc returns values of column HostID
                statement.setString(1, host.getHostFirstName());
                statement.setString(2, host.getHostLastName());
                statement.setString(3, host.getHostEmail());
                statement.setString(4, host.getHostTelephone());

                statement.executeUpdate();
                rs = statement.getGeneratedKeys();

                if (rs.next()) {
                    host.setId(rs.getInt(1));
                    statement = connection.prepareStatement("update PendingEvent set HostID = ? where PendingEventID = ?");

                    statement.setInt(1, host.getId());
                    statement.setInt(2, eventId);
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Release write from synchronization monitor
                        SynchronizationMonitor.getInstance().releaseWrite();

                        return true;
                    }
                    throw new Exception("Couldn't find existing host with id:" + host.getId() + " or pending eventId is wrong:" + eventId);
                }
                throw new Exception("Couldnt create new host");
            } else {
                throw new Exception("host name wrong:" + host.getHostFirstName() + " " + host.getHostLastName() +
                        " or email:" + host.getHostEmail());
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

    public boolean updateHost(Host host) {

        //checking if host is not null
        if(host != null) {
            // Acquire write from synchronization monitor
            SynchronizationMonitor.getInstance().acquireWrite();

            PreparedStatement statement = null; //statement to execute db query

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

                statement = connection.prepareStatement("update dbo.Host set FirstName = ?, LastName = ?, Email = ?, Telephone= ? " +
                        "where HostID = ?");
                statement.setString(1, host.getHostFirstName());
                statement.setString(2, host.getHostLastName());
                statement.setString(3, host.getHostEmail());
                statement.setString(4, host.getHostTelephone());
                statement.setInt(5, host.getId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                } else {
                    throw new Exception("Couldnt find host with id:" + host.getId());
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
        return false;
    }
}
