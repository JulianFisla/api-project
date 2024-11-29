package com.socialapp.restful.controller;

import com.socialapp.restful.domain.User;
import com.socialapp.restful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author JulianFisla
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Email already exists
        }
        // If userId is not provided, generate it
        if (user.getUserId() == 0) {
            user.setUserId(generateUniqueUserId());
        }
        // If createDate is not provided, set it to the current date
        if (user.getCreateDate() == null) {
            user.setCreateDate(new Date());
        }
        // If updateDate is not provided, set it to the current date
        if (user.getUpdateDate() == null) {
            user.setUpdateDate(new Date());
        }
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    private int generateUniqueUserId() {
        // Simple method to generate a unique userId
        return (int) (userRepository.count() + 1);
    }

    /**
     * Retrieve all users
     * @return
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Retrieve a user by userId
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing user
     * @param userId
     * @param userDetails
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setUpdateDate(new Date());
        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * Delete a user
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Additional endpoint: Find user by username
     * @param userName
     * @return
     */
    @GetMapping("/username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Additional endpoint: Delete user by username
     * @param userName
     * @return
     */
    @DeleteMapping("/username/{userName}")
    public ResponseEntity<Void> deleteUserByUserName(@PathVariable String userName) {
        if (!userRepository.findByUserName(userName).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
