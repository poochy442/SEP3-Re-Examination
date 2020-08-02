package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.Event;
import VIA.VIATalks.Database.data.Room;

import java.util.List;

public interface IRoomHandler {
    public List<Room> getRoomsForCampus(Campus campus);
    //public List<Room> getBookedRoomsForCampus(Campus campus);
    public boolean attachRoomToEvent(Room room, int eventId, int numberOfSeats);
    public boolean updateRoom(Event event, int roomId);


}
