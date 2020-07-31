package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Host;
import VIA.VIATalks.Database.jdbc.HostHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host")
public class HostController {

    private HostHandler handler;  //DAO for events

    public HostController() {
        handler = new HostHandler();
    }

    // POST: host/attach
    // Attaches host to event and returns whether or not it succeeded
    @PostMapping(path = "/attach")
    public boolean attachHost(@RequestBody Host host, @RequestParam(value = "eventId")int eventId) {
        return handler.attachHostToEvent(host, eventId);
    }

    // PUT: host/update
    // Updates host with the passed host using the db
    @PutMapping(path = "/update")
    public boolean updateHost(@RequestBody Host host) {
        return handler.updateHost(host);
    }


}
