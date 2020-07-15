package adapter;

import data.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomAdapter {

    List<Room> rooms; // Used to store the data, should be replaced with a database
    AtomicInteger counter; // Used to create ids

    // Constructor
    public RoomAdapter(){
        rooms = new ArrayList<>();
        counter = new AtomicInteger(getCurrentId()); // Initiate the AtomicInteger to start
    }

    /* CRUD methods. These should be implemented to contact the database */
    // CREATE method - Return true if successfully added, false if not
    public boolean addRoom(Room room){
        try{
            rooms.add(new Room(
                    counter.incrementAndGet(),
                    room.name,
                    room.capacity,
                    room.attending,
                    room.isFull));
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // READ method - returns the requested item, null if the item is not found
    public Room getRoom(int id){
        for(Room room : rooms){
            if(room.id == id){
                return room;
            }
        }
        return null;
    }

    // READ method - returns the stored List of data
    public List<Room> getAllData(){
        return rooms;
    }

    // UPDATE method - Returns true if the item was updated, false if the item isn't found
    public boolean setData(Room room){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).id == room.id){
                rooms.set(i, room);
                return true;
            }
        }
        return false;
    }

    // DELETE method - Returns true if the item was deleted, false if the item isn't found
    public boolean removeData(int id){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).id == id){
                rooms.remove(id);
                return true;
            }
        }
        return false;
    }

    /* Utility methods */
    // Returns the current max id
    public int getCurrentId(){
        // If the list is empty, return 0
        if(rooms.size() == 0)
            return 0;

        int maxId = 0;

        for(Room r : rooms){
            if(r.id > maxId){
                maxId = r.id;
            }
        }

        return maxId;
    }


}
