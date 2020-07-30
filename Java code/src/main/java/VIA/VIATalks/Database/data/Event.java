package VIA.VIATalks.Database.data;

import java.time.LocalDateTime;
import java.util.Date;

public class Event {

    //Data to store
    private int id;
    private String city;
    private String universityCampus; //address
    private String eventCategory;
    private String eventName;
    private String hostFirstName;
    private String hostLastName;
    private String hostEmail;
    private String hostTelephone;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private char campusBlock;
    private int roomNumber;
    private int numberOfSeats;

    //Constructor

    public Event(int id, String city, String universityCampus, String eventCategory, String eventName, String hostFirstName, String hostLastName,
                 String hostEmail, String hostTelephone, LocalDateTime startDate, LocalDateTime endDate, char campusBlock, int roomNumber, int numberOfSeats) {
        this.id = id;
        this.city = city;
        this.universityCampus = universityCampus;
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.hostFirstName = hostFirstName;
        this.hostLastName = hostLastName;
        this.hostEmail = hostEmail;
        this.hostTelephone = hostTelephone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campusBlock = campusBlock;
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getUniversityCampus() {
        return universityCampus;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public String getHostFirstName() {
        return hostFirstName;
    }

    public String getHostLastName() {
        return hostLastName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public String getHostTelephone() {
        return hostTelephone;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public char getCampusBlock() {
        return campusBlock;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
