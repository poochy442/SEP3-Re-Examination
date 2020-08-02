package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.jdbc.EventCategoryHandler;
import VIA.VIATalks.Database.jdbc.EventHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IEventCategoryHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IEventHandler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {

    private IEventHandler eventHandler;  //DAO for events
    private IEventCategoryHandler categoryHandler; //DAO for categories


    public EventController() {
        eventHandler = new EventHandler();
        categoryHandler = new EventCategoryHandler();
    }

    // GET: event/upcoming
    // Gets all upcoming events from the db and returns it
    @GetMapping(path = "/upcoming")
    public List<Event> getAllEvents(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date) {
        return eventHandler.getUpcomingEvents(date);
    }

<<<<<<< HEAD
=======
    //GET: event/request/all
    //Returns all pending events from db
    @GetMapping(path = "/request/all")
    public List<Event> getAllRequestedEvents() {
        return eventHandler.getAllRequestedEvents();
    }

>>>>>>> Java
    //GET: event/categoriy/all
    //Returns all event categories stored in db
    @GetMapping(path = "/category/all")
    public List<String> getAllEventCategories() {
        return categoryHandler.getAllEventCategories();
    }

    //GET: event/all/booked/room
    //Returns events which after the provided date
    //Event will rooms it is booked in
    @GetMapping(path = "/all/booked/room")
    public List<Event> getEventsBookedInRooms(@RequestParam(value="scheduleId") int scheduleID,
                                              @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date) {
        return eventHandler.getEventsBookedInRooms(scheduleID,date);
    }

    // POST: event/create
    // Adds the passed event using the db and returns whether or not it succeeded
    @PostMapping(path = "/create")
    public boolean createEvent(@RequestBody Event event) {
        return eventHandler.createEvent(event);
    }

<<<<<<< HEAD
=======
    // POST: event/create
    // Adds the passed event using the db and returns whether or not it succeeded
    @PostMapping(path = "/request")
    public boolean requestEvent(@RequestBody Event event) {
        return eventHandler.requestEvent(event);
    }

    //POST: event/request/accept
    //Deletes pending event and adds its info + attached room from C# to db
    @PostMapping(path = "request/accept")
    public boolean requestAccept(@RequestBody Event event) {
        if(eventHandler.createEvent(event)) {
            return eventHandler.deletePendingEvent(event.getId());
        }
        return false;
    }

>>>>>>> Java
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
        return eventHandler.updateEvent(event);
    }

    // DELETE: event/delete
    // Deletes the event with the passed id to be equal to the event's id which to be deleted from the db
    @DeleteMapping(path = "/delete")
    public boolean deleteEvent(@RequestParam(value = "id") int id) {
        return eventHandler.deleteEvent(id);
    }

<<<<<<< HEAD
=======
    // DELETE: event/pending/delete
    // Deletes the pending event with the passed id to be equal to the event's id which to be deleted from the db
    @DeleteMapping(path = "/pending/delete")
    public boolean deletePendingEvent(@RequestParam(value = "id") int id) {
        return eventHandler.deletePendingEvent(id);
    }

>>>>>>> Java
}
