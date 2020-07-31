package VIA.VIATalks.Database.data;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int id;
    private String ticketNumber;

    //Constructor
    public Ticket(int id, String ticketNumber) {
        this.id = id;
        this.ticketNumber = ticketNumber;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
