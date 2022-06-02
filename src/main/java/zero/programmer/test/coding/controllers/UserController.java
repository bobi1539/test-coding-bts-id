package zero.programmer.test.coding.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.UserResponse;
import zero.programmer.test.coding.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String welcome(){
        return "Welcome";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse create(@RequestBody User user){
        UserResponse response = userService.createUser(user);
        return response;
    }
}
