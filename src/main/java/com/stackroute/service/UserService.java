package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;

import java.util.List;

public interface UserService  {
    public User saveUser(User user) throws UserAlreadyExistsException;
    public User deleteUser(String email) throws UserNotFoundException;
    public User updatePhoneNumber(User user) throws UserNotFoundException;
    public List<User> getAllUsers();
}
