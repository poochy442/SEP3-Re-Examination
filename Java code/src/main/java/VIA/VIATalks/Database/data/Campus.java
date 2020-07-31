package VIA.VIATalks.Database.data;

import java.io.Serializable;

public class Campus implements Serializable {
    private int id;
    private String city;
    private int postalCode;
    private String address;

    //Constructor
    public Campus(int id, String city, int postalCode, String address) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
