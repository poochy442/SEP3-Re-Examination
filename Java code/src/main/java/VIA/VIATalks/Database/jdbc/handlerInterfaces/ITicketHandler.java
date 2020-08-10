package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Event;

import java.util.List;

public interface ITicketHandler {
    public boolean issueTicketForEvent(Event event, int userId);
    public boolean deleteTicketsForEvent(int eventId);
    //public List<Integer> getTicketsCountForEvents(List<Event> events);

}
