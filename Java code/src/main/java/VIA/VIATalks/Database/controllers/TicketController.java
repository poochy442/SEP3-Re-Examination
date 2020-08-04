package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.jdbc.TicketHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.ITicketHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private ITicketHandler handler;

    public TicketController() {
        handler = new TicketHandler();
    }

    //POST: ticket/issue
    //Creates a ticket for the provided event and stores it in the db
    @PostMapping("/issue")
    public boolean issueTicketForEvent(@RequestBody Event event, @RequestParam(value = "userId") int userId)
    {return handler.issueTicketForEvent(event,userId);}

    //getTicketsForEvents
    //getTicketForEvent
    //getTicketsCountForEvents
    //getTicketsCountForEvent
    //issueTicketForEvent
    //removeTicketsForEvent
    //removeTicketForEvent

}
