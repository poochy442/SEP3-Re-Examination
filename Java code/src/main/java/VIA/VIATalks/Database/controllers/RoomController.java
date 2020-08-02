package VIA.VIATalks.Database.controllers;

//import VIA.VIATalks.Database.adapter.RoomAdapter;
import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.RoomHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IRoomHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private IRoomHandler handler;  //DAO for events

    public RoomController() {
        handler = new RoomHandler();
    }

    //GET: room/campus
    //Gets all rooms of the campus
    @GetMapping(path = "/campus")
    public List<Room> getRoomsForCampus(@RequestBody Campus campus) {
        return handler.getRoomsForCampus(campus);
    }

    @GetMapping(path = "/booked")
    public List<Room> getBookedRoomsForCampus(@RequestBody Campus campus) {return handler.getBookedRoomsForCampus(campus);}

    // PUT: room/update
    // Updates room for the event in the db
    @PutMapping(path = "/update")
    public boolean updateRoom(@RequestBody Event event, @RequestParam(value = "roomId") int roomId) {
        return handler.updateRoom(event,roomId);
    }

}
