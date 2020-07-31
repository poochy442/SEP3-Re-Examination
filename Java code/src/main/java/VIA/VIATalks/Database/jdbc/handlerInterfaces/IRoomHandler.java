package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Room;

import java.util.List;

public interface IRoomHandler {
    public boolean attachRoomToEvent(Room room, int eventId);
    public List<Room> getRoomsForCampus(Campus campus);
    public boolean updateRoom(Event event, int roomId);
}
