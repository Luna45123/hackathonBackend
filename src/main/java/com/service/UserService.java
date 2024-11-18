package com.service;

import java.util.List;
import java.util.Map;

import com.domain.User;
import com.dto.LoginDTO;
import com.dto.UserDTO;
import com.response.LoginResponse;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
    void deleteUser(int id);
    User getUserById(int id);
    boolean updateUser(int id, UserDTO userDTO);
    boolean partialUpdateUser(int id, Map<String, Object> updates);
    List<User> getAllUsers();
}