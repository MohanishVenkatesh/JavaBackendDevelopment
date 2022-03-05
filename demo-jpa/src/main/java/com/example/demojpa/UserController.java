package com.example.demojpa;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    private static Logger logger = LogManager.getLogger();


    @GetMapping("/users")
    public List<User> getUsers() throws SQLException {
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws SQLException {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        try {
            userService.createUser(userCreateRequest);
            return new ResponseEntity<>("User is created", HttpStatus.CREATED);

        } catch (SQLException exception) {
            return new ResponseEntity<>(exception.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId) throws SQLException {
        return userService.deleteUser(userId);
    }

    @PostMapping("/parse-file")
    public ResponseEntity<String> parseFile(HttpServletRequest httpServletRequest) throws ServletException, IOException, SQLException {
        Part filePart = httpServletRequest.getPart("my_file");
        Part textPart = httpServletRequest.getPart("my_text");

        InputStream inputStream = textPart.getInputStream();
        String result = IOUtils.toString(inputStream , StandardCharsets.UTF_8);
        logger.info("The key of the text is {} and the value of the text is {}" , result ,textPart.getName()) ;

        InputStream inputStream1 = filePart.getInputStream();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("firstName","lastName","age","email"); // we can each column to a name and extract that record
        CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream1) , csvFormat  );
        List<CSVRecord>  records = csvParser.getRecords();
        List<UserCreateRequest> userCreateRequests = new ArrayList<>();
        for(int i = 1 ; i < records.size() ; i ++){ // since we need to skip the headers we are starting with i=1
            logger.info("record no {} ids {}", i , records.get(i));
            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .firstName(records.get(i).get(0))
                    .lastName(records.get(i).get(1))
                    .age(Integer.parseInt(records.get(i).get(2)))
                    .email(records.get(i).get(3)).build();;
                    userCreateRequests.add(userCreateRequest);
        }
        List <User> usersCreated = userService.createBulkUsers(userCreateRequests);
        return new ResponseEntity<>(usersCreated.size() + "users are created in the system", HttpStatus.OK);



    }
}
