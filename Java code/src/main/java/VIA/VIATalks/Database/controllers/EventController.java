package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.adapter.EventAdapter;
import VIA.VIATalks.Database.data.Event;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventAdapter adapter;  // Adapter for data access

    public EventController() {
        adapter = new EventAdapter();
    }

    // GET: event/all
    // Gets all events from the adapter and returns it
    @GetMapping(path = "/all")
    public List<Event> getAllEvents(){ return adapter.getAllEvents(); }

    // POST: event/create
    // Adds the passed event using the adapter and returns whether or not it succeeded
    @PostMapping(path = "/create")
    public boolean createEvent(@RequestBody Event event){ return adapter.addEvent(event); }

    // PUT: event/update
    // Updates the event with the passed id to be equal to the passed event using the adapter
    @PutMapping(path = "/update")
    public boolean updateEvent(@RequestBody Event event){
        return adapter.updateEvent(event);
    }

    // DELETE: event/delete
    // Deletes the event with the passed id to be equal to the event's id which to be deleted from the adapter
    @DeleteMapping(path = "/delete")
    public boolean deleteEvent(@RequestParam(value="id") int id) {return adapter.deleteEvent(id);}
}
