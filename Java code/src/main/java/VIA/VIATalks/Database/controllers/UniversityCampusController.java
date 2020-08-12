package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.data.Campus;
import VIA.VIATalks.Database.data.University;
import VIA.VIATalks.Database.jdbc.UniversityCampusHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUniversityCampusHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityCampusController {

    private IUniversityCampusHandler handler; //DAO for events

    public UniversityCampusController() {
        handler = UniversityCampusHandler.getInstance();
    }

    // GET: university/all
    // Returns list of universities stored in db
    @GetMapping(path = "/all")
    public List<University> getAllUniversities() {
        return handler.getAllUniversities();
    }

    // GET: university/campus/all
    // Returns list of campuses of provided university stored in db
    @GetMapping(path = "/campus/all")
    public List<Campus> getCampusesForUniversity(@RequestBody University university) {
        return handler.getCampusesForUniversity(university);
    }
}
