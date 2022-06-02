package zero.programmer.test.coding.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zero.programmer.test.coding.entitites.User;
import zero.programmer.test.coding.models.LoginRequest;
import zero.programmer.test.coding.models.UserResponse;
import zero.programmer.test.coding.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> create(@RequestBody User user){
        UserResponse response = userService.createUser(user);
        return ResponseEntity.ok().body(
                response
        );
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listUser = userService.getAllUser();
        return ResponseEntity.ok().body(
                listUser
        );
    }

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> signIn(@RequestBody LoginRequest loginRequest){
        UserResponse userResponse = userService.signIn(loginRequest);
        return ResponseEntity.ok().body(
                userResponse
        );
    }
}
