package VIA.VIATalks.Database.controllers;

//import VIA.VIATalks.Database.adapter.RoomAdapter;
import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Room;
import VIA.VIATalks.Database.jdbc.RoomHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomHandler handler;  //DAO for events

    public RoomController() {
        handler = new RoomHandler();
    }

    //GET: room/campus
    //Gets all rooms of the campus
    @GetMapping(path = "/campus")
    public List<Room> getRoomsForCampus(@RequestBody Campus campus) {
        return handler.getRoomsForCampus(campus);
    }

    // POST: room/attach
    // Attaches room to event and returns whether or not it succeeded
    @PostMapping(path = "/attach")
    public boolean attachRoom(@RequestBody Room room, @RequestParam(value = "eventId")int eventId) {
        return handler.attachRoomToEvent(room, eventId);
    }

}
