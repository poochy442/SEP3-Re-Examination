package VIA.VIATalks.Database.data;

import java.time.LocalDateTime;

public class Schedule {
    //Data to store
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    //Constructor
    public Schedule(int id, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
