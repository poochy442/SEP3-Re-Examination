package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.jdbc.HostHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IHostHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/host")
public class HostController {

    private IHostHandler handler;  //DAO for events

    public HostController() {
        handler = HostHandler.getInstance();
    }

    //GET: host/all
    //Returns all hosts stored in db
    @GetMapping(path = "/all")
    public List<Host> getAllHosts(){return handler.getAllHosts();}

    // PUT: host/update
    // Updates host with the passed host using the db
    @PutMapping(path = "/update")
    public boolean updateHost(@RequestBody Host host) {
        return handler.updateHost(host);
    }


}
