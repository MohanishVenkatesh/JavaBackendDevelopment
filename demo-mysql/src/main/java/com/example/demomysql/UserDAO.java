package com.example.demomysql;

import org.apache.catalina.startup.ConnectorCreateRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private Connection connection;
    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://10.17.3.14:3306/jbdl", "root", "root");
        logger.info("connected");
       createTable();

    }

    public void createTable() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute("create table IF NOT EXISTS user123 (id int primary key auto_increment  , firstName varchar(30) , lastName varchar(30) , email varchar(49),  age int , isSeniorCitizen boolean )");
    }

    public void create(User user) throws SQLException {

        PreparedStatement statement = this.connection.prepareStatement("insert into user123(firstName, lastName, age , email , isSeniorCitizen  ) values (?,?,?,?,?)");
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setInt(3, user.getAge());
        statement.setString(4,user.getEmail());
        statement.setBoolean(5, user.isSeniorCitizen());
        boolean result = statement.execute();

        logger.debug("Result Set Obtained from the Query ? {}" , result);


    }

    public List<User> get() throws SQLException {
        List<User> userList = new ArrayList<>() ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while(resultSet.next())
        {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");
            boolean isSeniorCitizen = resultSet.getBoolean("isSeniorCitizen");
            User user = User.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .age(age)
                    .isSeniorCitizen(isSeniorCitizen)
                    .build();
            userList.add(user);
        }
        return userList;

    }

    public User getById(int id) throws SQLException {
        User user = null ;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user where id = " + id);
        while(resultSet.next())
        {
            int userId = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            int age = resultSet.getInt("age");
            boolean isSeniorCitizen = resultSet.getBoolean("isSeniorCitizen");
            return User.builder()
                    .id(userId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .age(age)
                    .isSeniorCitizen(isSeniorCitizen)
                    .build();
        }
        return null;

    }

    public User delete(int id) throws SQLException {
        User user = getById(id);
        Statement statement = this.connection.createStatement();
        int rowsUpdated = statement.executeUpdate("delete from user where id = " + id );
        logger.debug("Number of rows deleted:" + rowsUpdated);
        return user ;
    }
}
