package VIA.VIATalks.Database.data;

import java.io.Serializable;

public class Host implements Serializable {
    private int id;
    private String hostFirstName;
    private String hostLastName;
    private String hostEmail;
    private String hostTelephone;

    //Constructor
    public Host(int id, String hostFirstName, String hostLastName, String hostEmail, String hostTelephone) {
        this.id = id;
        this.hostFirstName = hostFirstName;
        this.hostLastName = hostLastName;
        this.hostEmail = hostEmail;
        this.hostTelephone = hostTelephone;
    }

    //Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostFirstName() {
        return hostFirstName;
    }

    public void setHostFirstName(String hostFirstName) {
        this.hostFirstName = hostFirstName;
    }

    public String getHostLastName() {
        return hostLastName;
    }

    public void setHostLastName(String hostLastName) {
        this.hostLastName = hostLastName;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
    }

    public String getHostTelephone() {
        return hostTelephone;
    }

    public void setHostTelephone(String hostTelephone) {
        this.hostTelephone = hostTelephone;
    }
}
