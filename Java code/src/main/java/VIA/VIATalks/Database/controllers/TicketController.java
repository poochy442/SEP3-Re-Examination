package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.jdbc.TicketHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketHandler handler;

    public TicketController() {
    }

    //getTicketsForEvents
    //getTicketForEvent
    //getTicketsCountForEvents
    //getTicketsCountForEvent
    //issueTicketForEvent
    //removeTicketsForEvent
    //removeTicketForEvent

}
