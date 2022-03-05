package com.example.demorestapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static HashMap<Integer, User> userHashMap = new HashMap<>();

    @PostMapping("/user") // this is request body
    public void createUser(@RequestBody User user) {
        userHashMap.put(user.getId(), user);
    }


    @PostMapping("/user2")
    public void createUser2(@RequestParam("id") int id,
                            @RequestParam("name") String name,
                            @RequestParam("age") int age) {
        userHashMap.put(id,User.builder().id(id).name(name).age(age).build());
    }

    @PostMapping("/user3/id/{id}/name/{name}/age/{age}")
    public void createUser3(@PathVariable("id") int id,
                            @PathVariable("name") String name,
                            @PathVariable("age") int age) {
        userHashMap.put(id, User.builder().id(id).name(name).age(age).build());
    }

    @GetMapping("/users")
    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    @GetMapping("/user")
    public User  getUserHashMap2(@RequestParam("id") int id) {
        return userHashMap.get(id);

    }
    @DeleteMapping("/user")
    public User deleteuser(@RequestParam("id") int id)
    {
        return userHashMap.remove(id);
    }
    @PutMapping("/user")
    public User updateuser(@RequestParam("id") int givenId,
                           @RequestBody User user)
    {
        userHashMap.putIfAbsent(givenId , user);
        User reterivedUser = userHashMap.get(givenId);
        if(user.getId() != null)
        {
            reterivedUser.setId(user.getId());

        }
        if(user.getAge() != null)
        {
            reterivedUser.setAge(user.getAge());
        }
        if(user.getName() != null)
        {
            reterivedUser.setName(user.getName());
        }
        logger.info ("retrieved user from map {}" , reterivedUser);
        logger.info("getting user again from map {}" , userHashMap.get(givenId ) );
        return reterivedUser ;
    }



}
