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

    //Constructor
    public Event(int id, String eventCategory, String eventName, LocalDateTime startDate, LocalDateTime endDate, int numberOfSeats) {
        this.id = id;
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfSeats = numberOfSeats;
    }

    //Getters and Setters

    public int getId() {
        return id;
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
}
