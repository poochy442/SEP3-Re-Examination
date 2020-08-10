package VIA.VIATalks.Database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseConnection {
    private String dbConnectionString;
    private static DatabaseConnection instance;
    private static Lock lock = new ReentrantLock();

    private DatabaseConnection() {
        dbConnectionString = "jdbc:sqlserver://LAPTOP-D5VQT9SU:1433;databaseName=SEP3re;user=sep3re_admin;password=29072020";
    }

    public static DatabaseConnection getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnectionToDB() throws SQLException {
        return DriverManager.getConnection(dbConnectionString);
    }
}
