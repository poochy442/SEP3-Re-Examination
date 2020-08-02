package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Campus;

public interface IScheduleHandler {
    public boolean attachScheduleToEvent(Campus campus, int eventId);
<<<<<<< HEAD
=======
    public boolean attachScheduleToPendingEvent(Campus campus, int eventID);
>>>>>>> Java
}
