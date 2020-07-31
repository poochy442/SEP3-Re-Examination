package VIA.VIATalks.Database.data;

import java.io.Serializable;

public class University implements Serializable {
    private int id;
    private String name;
    private String country;

    //Constructor
    public University(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
