package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService implements Serializable {
    @Autowired
    UserDAO userDAO;
    public void createUser( UserCreateRequest userCreateRequest) throws SQLException {
        userDAO.save(userCreateRequest.toUser());


    }
    public List<User> createBulkUsers( List<UserCreateRequest> userCreateRequests) throws SQLException{
        List <User> users = userCreateRequests.stream() // it makes the UserCreateRequest to a stream
                .map(userCreateRequest -> userCreateRequest.toUser())  // this maps userCreateRequest to the converted user object
                .collect(Collectors.toList()); // this collects it and converts the stream to a List
        return userDAO.saveAll(users);
    }

    public User  deleteUser(int userId) throws SQLException {
        User user = userDAO.findById(userId).orElse(null);
        userDAO.deleteById(userId);
        return  user ;
    }

    public List<User> getUsers() throws SQLException {
        return userDAO.findAll();
    }

    public User getUserById(int userId) throws SQLException {
        return  userDAO.findById(userId).orElse(null);

    }
}
