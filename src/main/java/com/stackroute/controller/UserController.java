package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;

        try{
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("succcessfully created", HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            User user1 = userService.updatePhoneNumber(user);
            return new ResponseEntity<User>(user1, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable("name") String name) {
        ResponseEntity responseEntity;
        try {
            User user = userService.deleteUser(name);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

}
