package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Host;

import java.util.List;

public interface IHostHandler {
    public List<Host> getAllHosts();
    public boolean attachHostToEvent(Host host, int eventId);
    public boolean attachHostToPendingEvent(Host host, int eventID);
    public boolean updateHost(Host host);

}
