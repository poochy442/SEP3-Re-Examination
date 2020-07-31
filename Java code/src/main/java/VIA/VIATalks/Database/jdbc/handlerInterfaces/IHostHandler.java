package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Host;

public interface IHostHandler {
    public boolean attachHostToEvent(Host host, int eventId);
    public boolean updateHost(Host host);

}
