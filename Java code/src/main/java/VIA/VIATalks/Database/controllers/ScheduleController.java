package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Schedule;
import VIA.VIATalks.Database.jdbc.ScheduleHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleHandler handler;  //DAO for events

    public ScheduleController() {
        handler = new ScheduleHandler();
    }

    // POST: host/attach
    // Attaches schedule to event and returns whether or not it succeeded
    @PostMapping(path = "/attach")
    public boolean attachSchedule(@RequestParam(value = "eventId") int eventId) {
        return handler.attachScheduleToEvent(eventId);
    }
}
