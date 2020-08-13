package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import java.util.List;

public interface IUserHandler {
    public List<Boolean> createUser(String email, String password);
    public List<Boolean> login(String email, String password);
}
