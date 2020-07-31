package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.jdbc.EventCategoryHandler;
import VIA.VIATalks.Database.jdbc.EventHandler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {

    private EventHandler handler;  //DAO for events
    private EventCategoryHandler categoryHandler; //DAO for categories


    public EventController() {
        handler = new EventHandler();
        categoryHandler = new EventCategoryHandler();
    }

    // GET: event/upcoming
    // Gets all upcoming events from the db and returns it
    @GetMapping(path = "/upcoming")
    public List<Event> getAllEvents(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date) {
        return handler.getUpcomingEvents(date);
    }

    //GET: event/categoriy/all
    //Returns all event categories stored in db
    @GetMapping(path = "/category/all")
    public List<String> getAllEventCategories() {
        return categoryHandler.getAllEventCategories();
    }

    // POST: event/create
    // Adds the passed event using the db and returns whether or not it succeeded
    @PostMapping(path = "/create")
    public boolean createEvent(@RequestBody Event event) {
        return handler.createEvent(event);
    }

    // PUT: event/category/update
    // Updates the event category with the passed  event and category name using the db
    @PutMapping(path = "category/update")
    public boolean updateEventCategory(@RequestBody Event event, @RequestParam(value = "category") String category) {
        return categoryHandler.updateEventCategory(event,category);
    }

    // PUT: event/update
    // Updates the event with the passed  event using the db
    @PutMapping(path = "/update")
    public boolean updateEvent(@RequestBody Event event) {
        return handler.updateEvent(event);
    }

    // DELETE: event/delete
    // Deletes the event with the passed id to be equal to the event's id which to be deleted from the db
    @DeleteMapping(path = "/delete")
    public boolean deleteEvent(@RequestParam(value = "id") int id) {
        return handler.deleteEvent(id);
    }

}
