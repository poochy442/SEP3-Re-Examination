package VIA.VIATalks.Database.data;

import java.util.Date;

public class Event {

    //Data to store
    private int id;
    private String city;
    private String universityCampus;
    private String eventCategory;
    private String eventName;
    private String hostName;
    private String hostEmail;
    private String hostTelephone;
    private Date startDate;
    private Date endDate;
    private char campusBlock;
    private int roomNumber;

    //Constructor

    public Event(int id, String city, String universityCampus, String eventCategory, String eventName, String hostName, String hostEmail,
                 String hostTelephone, Date startDate, Date endDate, char campusBlock, int roomNumber) {
        this.id = id;
        this.city = city;
        this.universityCampus = universityCampus;
        this.eventCategory = eventCategory;
        this.eventName = eventName;
        this.hostName = hostName;
        this.hostEmail = hostEmail;
        this.hostTelephone = hostTelephone;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campusBlock = campusBlock;
        this.roomNumber = roomNumber;
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

    public String getHostName() {
        return hostName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public String getHostTelephone() {
        return hostTelephone;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public char getCampusBlock() {
        return campusBlock;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
