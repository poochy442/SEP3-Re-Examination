package VIA.VIATalks.Database.controllers;

import VIA.VIATalks.Database.jdbc.UserHandler;
import VIA.VIATalks.Database.jdbc.handlerInterfaces.IUserHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserHandler userHandler;

    public UserController(){
        userHandler = UserHandler.getInstance();
    }

    @GetMapping(path = "/login")
    public List<Boolean> login(@RequestParam(value = "email") String email,
                               @RequestParam(value = "password") String password){
        return userHandler.login(email, password);
    }

    @PostMapping(path = "/create")
    public List<Boolean> create(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String password){
        return userHandler.createUser(email, password);
    }
}
