package VIA.VIATalks.Database.data;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable {

    //Data to store
    private int id;
    private String eventCategory;
    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int numberOfSeats;
    private int registeredUsers;

    private Host host;
    private Room room;
    private Campus campus;

    //default Constructor for JSON deserialization
    public Event() {
    }

    //Constructor
    public Event(int id, String eventCategory, String eventName, LocalDateTime startDate, LocalDateTime endDate, int numberOfSeats) {
        this.id = id;
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeats = numberOfSeats;
    }

    //Constructor
    public Event(int id, String eventCategory, String eventName, LocalDateTime startDate, LocalDateTime endDate, int numberOfSeats, int registeredUsers, Host host, Room room, Campus campus) {
        this.id = id;
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeats = numberOfSeats;
        this.registeredUsers = registeredUsers;
        this.host = host;
        this.room = room;
        this.campus = campus;
    }


    //Getters and Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(int registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
