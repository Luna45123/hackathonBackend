package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import com.domain.User;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.repository.UserRepository;
import com.response.LoginResponse;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public String addUser(UserDTO userDTO) {
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            return "User already exists";
        }
        User user = new User(
                userDTO.getId(),
                userDTO.getEname(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getMembership());
        userRepo.save(user);
        return "User added successfully";
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        // Check if user exists with the given email and password
        Optional<User> user = userRepo.findOneByEmailAndPassword(
                loginDTO.getEmail(),
                loginDTO.getPassword());
        
        if (user.isPresent()) {
            // If user is found, return a successful response
            User foundUser = user.get();

            // Create a UserDTO with user details
            UserDTO userDTO = new UserDTO(
                foundUser.getId(),
                foundUser.getName(),
                foundUser.getEmail(),
                foundUser.getPassword(),
                foundUser.getMembership()
            );
            
            // Returning a successful LoginResponse with a user-specific message
            return new LoginResponse(true, "Login successful");
        }
        
        // If credentials are invalid, return a failed response
        return new LoginResponse(false, "Invalid credentials");
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);  // Return null if user is not found
    }

    @Override
    public boolean updateUser(int id, UserDTO userDTO) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDTO.getEname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setMembership(userDTO.getMembership());
            userRepo.save(user);  // Save updated user
            return true;
        }
        return false;  // Return false if user is not found
    }

    @Override
    public boolean partialUpdateUser(int id, Map<String, Object> updates) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            
            // Update each field if it's present in the updates map
            if (updates.containsKey("name")) {
                user.setName((String) updates.get("name"));
            }
            if (updates.containsKey("email")) {
                user.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("password")) {
                user.setPassword((String) updates.get("password"));
            }
            if (updates.containsKey("membership")) {
                user.setMembership((int) updates.get("membership"));
            }
            userRepo.save(user);  // Save partial updates
            return true;
        }
        return false;  // Return false if user is not found
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();  // Return all users
    }

}
