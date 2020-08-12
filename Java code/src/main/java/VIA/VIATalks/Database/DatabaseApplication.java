package VIA.VIATalks.Database;

import VIA.VIATalks.Database.jdbc.SynchronizationMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

}
