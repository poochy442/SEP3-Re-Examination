package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IEventCategoryHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventCategoryHandler implements IEventCategoryHandler {
    //implementing singleton
    private static EventCategoryHandler instance;
    private static Lock lock = new ReentrantLock();


    //private constructor for singleton implementation
    private EventCategoryHandler() {
    }

    //getInstance method for singleton implementation
    public static EventCategoryHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new EventCategoryHandler();
                }
            }
        }
        return instance;
    }

    public List<String> getAllEventCategories() {
        // Acquire read from synchronization monitor
        SynchronizationMonitor.getInstance().acquireRead();

        List<String> categories = new ArrayList<>(); //holds event categories
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {

            statement = connection.prepareStatement("select * from dbo.EventCategory");
            rs = statement.executeQuery();

            //go through all categories returned to result set
            while (rs.next()) {

                //add new category to categories list
                categories.add(rs.getString("Name"));
            }
            // Release read from synchronization monitor
            SynchronizationMonitor.getInstance().releaseRead();

            return categories;

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


    /*public List<String> getEventCategoriesById(List<Integer> categoryIds) {
        List<String> categories = new ArrayList<>();  //holds event categories names
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            statement = connection.prepareStatement("select Name from dbo.EventCategory where EventCategoryID = ?");

            //go through all categories and et the name
            for (int i = 0; i < categoryIds.size(); i++) {
                statement.setInt(1, categoryIds.get(i));
                rs = statement.executeQuery();
                if (rs.next()) {
                    categories.add(rs.getString("Name"));
                } else {
                    throw new Exception("Event Category Id NOT in Database" + categoryIds.get(i));
                }
            }
            return categories;
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

    }*/

    public boolean attachCategoryToEvent(String category,int eventID) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        int categoryID = 0; //holds host id
        PreparedStatement statement = null; //statement to execute db query

        categoryID = getEventCategoryIdByName(category);

        //checking if provided category exists in db
        if (categoryID > 0) {

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                statement = connection.prepareStatement("update Event set EventCategoryID = ? where EventID = ?");

                statement.setInt(1, categoryID);
                statement.setInt(2, eventID);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                }
                throw new Exception("Couldn't find existing event with eventId:" + eventID);

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
        // Release write from synchronization monitor
        SynchronizationMonitor.getInstance().releaseWrite();

        return false;
    }

    public boolean attachCategoryToPendingEvent(String category, int eventID) {
        // Acquire write from synchronization monitor
        SynchronizationMonitor.getInstance().acquireWrite();

        int categoryID = 0; //holds host id
        PreparedStatement statement = null; //statement to execute db query

        categoryID = getEventCategoryIdByName(category);

        //checking if provided category exists in db
        if (categoryID > 0) {

            try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
                statement = connection.prepareStatement("update PendingEvent set EventCategoryID = ? where PendingEventID = ?");

                statement.setInt(1, categoryID);
                statement.setInt(2, eventID);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Release write from synchronization monitor
                    SynchronizationMonitor.getInstance().releaseWrite();

                    return true;
                }

                throw new Exception("Couldn't find existing pending event with eventId:" + eventID);

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
        // Release write from synchronization monitor
        SynchronizationMonitor.getInstance().releaseWrite();

        return false;
    }

    private int getEventCategoryIdByName(String category) {
        PreparedStatement statement = null; //statement to execute db query
        ResultSet rs = null; //result set to get from executing db query

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {
            statement = connection.prepareStatement("select EventCategoryID from dbo.EventCategory where Name = ?");

            statement.setString(1, category);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("EventCategoryID");
            }

            throw new Exception("Event Category Id not found for category:" + category);

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

    public boolean updateEventCategory(Event event, String category) {
        //checking if event is not null
        if(event != null) {
            // Acquire write from synchronization monitor
            SynchronizationMonitor.getInstance().acquireWrite();

            int categoryID = getEventCategoryIdByName(category);

            if(categoryID > 0) {
                PreparedStatement statement = null;

                try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()) {


                    statement = connection.prepareStatement("update dbo.Event set EventCategoryID = ? where EventID = ?");
                    statement.setInt(1, categoryID);
                    statement.setInt(2, event.getId());

                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Release write from synchronization monitor
                        SynchronizationMonitor.getInstance().releaseWrite();

                        return true;
                    }
                    else {
                        throw new Exception("Couldn't find event with id:" + event.getId());
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
        return false;
    }
}
