package data;

public class Room {

    // Data to store
    public int id;
    public String name;
    public int capacity;
    public int attending;
    public boolean isFull;

    // Constructor
    public Room(int id, String name, int capacity, int attending, boolean isFull) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.attending = attending;
        this.isFull = isFull;
    }

    public boolean addUser() {
        if(isFull)  // Check if Room is full before adding user
            return false;

        // Change variable
        attending++;

        if(attending >= capacity)   // Check if Room is full after adding user
            isFull = true;

        return true;
    }

    public boolean removeUser(){
        if(attending <= 0)  // Check if Room is already empty
            return false;

        // Change variables
        attending--;
        isFull = false;

        return true;
    }

}
