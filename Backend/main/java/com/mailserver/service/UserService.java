package com.mailserver.service;

import com.mailserver.model.Mail;
import com.mailserver.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private HashMap<String,User> users;

    public UserService() {
        users = new HashMap<>();
    }
    public User addUser(User user) {
        users.put(user.getEmail(),user);
        return user;
    }
    public User getUserByEmail(String email) {
        return users.get(email);
    }
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
