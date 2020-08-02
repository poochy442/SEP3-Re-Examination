package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventHandler {
    public List<Event> getUpcomingEvents(LocalDateTime date);
    public List<Event> getEventsBookedInRooms(int scheduleID, LocalDateTime date);
    public boolean createEvent(Event event);
    public boolean requestEvent(Event event);
    public boolean updateEvent(Event event);
    public boolean deleteEvent(int id);
    public boolean deletePendingEvent(int id);
}
