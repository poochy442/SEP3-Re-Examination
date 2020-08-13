package VIA.VIATalks.Database.jdbc;

import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUserHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHandler implements IUserHandler {

    //implementing singleton
    private static UserHandler instance;
    private static Lock lock = new ReentrantLock();

    private UserHandler(){

    }

    //getInstance method for singleton implementation
    public static UserHandler getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new UserHandler();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Boolean> createUser(String email, String password) {
        List<Boolean> returnList = new ArrayList<>();
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()){

            statement = connection.prepareStatement(
                    "INSERT INTO dbo.Users(FirstName, LastName, Email, Password) VALUES(?,?,?,?)");
            statement.setString(1, "testFirstName");
            statement.setString(2, "testLastName");
            statement.setString(3, email);
            statement.setString(4, password);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                returnList.add(true);
            } else {
                returnList.add(false);
            }

            returnList.add(isAdmin(email));
        } catch (Exception e){
            e.printStackTrace();
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

        SynchronizationMonitor.getInstance().releaseWrite();
        return returnList;
    }

    @Override
    public List<Boolean> login(String email, String password) {
        List<Boolean> returnList = new ArrayList<>();
        SynchronizationMonitor.getInstance().acquireWrite();

        PreparedStatement statement = null;
        ResultSet rs = null;

        try (Connection connection = DatabaseConnection.getInstance().getConnectionToDB()){

            statement = connection.prepareStatement(
                    "SELECT * FROM dbo.User WHERE Email = ? AND Password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            rs = statement.executeQuery();

            if(rs.next() == true){
                returnList.add(true);
            } else {
                returnList.add(false);
            }

            returnList.add(isAdmin(email));
        } catch (Exception e){
            e.printStackTrace();
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

        SynchronizationMonitor.getInstance().releaseWrite();
        return returnList;
    }

    private boolean isAdmin(String email){
        Pattern p = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9.]");
        Matcher m = p.matcher(email);

        if(m.matches()){ // Is email
            return true;
        } else {
            return false;
        }
    }
}
