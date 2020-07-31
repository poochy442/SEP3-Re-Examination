package VIA.VIATalks.Database.jdbc.handlerInterfaces;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.University;

import java.util.List;

public interface IUniversityCampusHandler {
    public List<University> getAllUniversities();
    public List<Campus> getCampusesForUniversity(University university);

}
