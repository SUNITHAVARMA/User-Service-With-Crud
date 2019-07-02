package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getEmail())){
            throw new UserAlreadyExistsException("user already exists");
        }
        User savedUser=userRepository.save(user);
        if(savedUser==null){
            throw new UserAlreadyExistsException("user already exists");
        }
        return savedUser;
    }

     @Override
     public User updatePhoneNumber(User user) throws UserNotFoundException {
         Optional optional=userRepository.findById(user.getEmail());
         if (optional.isPresent()) {
             User user1 = userRepository.findById(user.getEmail()).get();
             user1.setPhoneNumber(user.getPhoneNumber());
             userRepository.save(user1);
             return user1;
         } else {
             throw new UserNotFoundException("user not found");
         }

     }



     @Override
     public User deleteUser(String email) throws UserNotFoundException {
        if(userRepository.existsById(email)){
            userRepository.deleteById(email);
        }else {
            throw new UserNotFoundException("Exception in deleteUser");
        }
         return null;
     }

     @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
