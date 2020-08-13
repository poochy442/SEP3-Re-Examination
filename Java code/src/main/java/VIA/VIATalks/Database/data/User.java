package VIA.VIATalks.Database.data;

public class User {
    private String email;
    private String password;

    // Constructor
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    // Getter-Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
