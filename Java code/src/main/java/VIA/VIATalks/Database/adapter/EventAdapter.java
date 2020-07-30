package VIA.VIATalks.Database.adapter;

import VIA.VIATalks.Database.data.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EventAdapter {

    List<Event> events; // Used to store the data, should be replaced with a database
    AtomicInteger counter; // Used to create ids

    // Constructor
    public EventAdapter(){
        events = new ArrayList<>();
        counter = new AtomicInteger(getCurrentId()); // Initiate the AtomicInteger to start
    }

    //Create new event - Return true if successfully added, false if not
    public boolean addEvent(Event event) {
        try{
            events.add(new Event(
                    counter.incrementAndGet(),
                    event.getCity(),
                    event.getUniversityCampus(),
                    event.getEventCategory(),
                    event.getEventName(),
                    event.getHostFirstName(),
                    event.getHostLastName(),
                    event.getHostEmail(),
                    event.getHostTelephone(),
                    event.getStartDate(),
                    event.getEndDate(),
                    event.getCampusBlock(),
                    event.getRoomNumber(),
                    event.getNumberOfSeats()
            ));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ method - returns the stored List of data
    public List<Event> getAllEvents(){
        return events;
    }


    // UPDATE method - Returns true if the item was updated, false if the item isn't found
    public boolean updateEvent(Event event){
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getId() == event.getId()){
                events.set(i, event);
                return true;
            }
        }
        return false;
    }

    //DELETE method - Returns true if the item was deleted, false if the item isn't found
    public boolean deleteEvent(int id) {
        for(int i=0;i<events.size();i++) {
            if(events.get(i).getId() == id) {
                events.remove(i);
                return true;
            }
        }
        return false;
    }

    /* Utility methods */
    // Returns the current max id
    public int getCurrentId(){
        // If the list is empty, return 0
        if(events.size() == 0)
            return 0;

        int maxId = 0;

        for(Event e : events){
            if(e.getId() > maxId){
                maxId = e.getId();
            }
        }

        return maxId;
    }
}
