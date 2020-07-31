package VIA.VIATalks.Database.data;

import java.io.Serializable;

public class Room implements Serializable {

    // Data to store
    private int id;
    private int roomNumber;
    private char block;
    private int capacity;
    private double area;


    // Constructor
    public Room(int id, int roomNumber, char block, int capacity, double area) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.block = block;
        this.capacity = capacity;
        this.area = area;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public char getBlock() {
        return block;
    }

    public void setBlock(char block) {
        this.block = block;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
