package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.adapter.RoomAdapter;
import VIA.VIATalks.Database.data.Room;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomAdapter adapter; // Adapter for data access

    // Constructor
    public RoomController(){
        adapter = new RoomAdapter();
    }

    // GET: api/room
    // Gets all data from the adapter and returns it
    @GetMapping
    public List<Room> getAllData(){
        return adapter.getAllData();
    }

    // GET: api/data/get
    // Gets the requested data from the adapter and returns it
    @GetMapping(path = "/get")
    public Room getData(@RequestParam(value = "id") int id){
        return adapter.getRoom(id);
    }

    // POST: api/data/add
    // Adds the passed data using the adapter and return whether or not it succeeded
    @PostMapping(path = "/add")
    public boolean addData(@RequestParam(value = "room") Room room){
        return adapter.addRoom(room);
    }

    // PUT: api/data/update
    // Updates the data with the passed id to be equal to the passed data using the adapter
    @PutMapping(path = "/update")
    public boolean updateData(@RequestParam(value = "room") Room room){
        return adapter.setData(room);
    }

    // DELETE: api/data/delete
    // Removes the data with the passed id using the adapter
    // TODO - Add security!
    @DeleteMapping(path = "/delete")
    public boolean removeData(@RequestParam(value = "id") int id){
        return adapter.removeData(id);
    }

}
