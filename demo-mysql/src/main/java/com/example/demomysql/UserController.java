package com.example.demomysql;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<User> getUsers() throws SQLException {
        //TODO: Return all the users in system
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws SQLException {
        // TODO: Return user within given id
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public void createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) throws SQLException {
        userService.createUser(userCreateRequest);
        // TODO : Creating a new user with given details

    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId) throws SQLException {
        //TODO: Deleting a user
        return userService.deleteUser(userId);
    }
}