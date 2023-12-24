package com.mailserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailserver.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    private static UserService instance = new UserService();
    private HashMap<String,User> users;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserService() {
        users = new HashMap<>();
    }
    //Singleton instance to be used in the whole application
    public static UserService getInstance() {
        if(instance == null)
            instance = new UserService();
        return instance;
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

    public void saveUsers(){
        try{
            objectMapper.findAndRegisterModules();
            objectMapper.writeValue(new File("users.json"),this.getAllUsers());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void LoadUsers(){
        try{
            objectMapper.findAndRegisterModules();
            List<User> users = objectMapper.readValue(new File("users.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

            users.forEach(this::addUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean logIn(String email, String userName) {
        if(getUserByEmail(email) == null)
            return false;
        return getUserByEmail(email).getUserName().equals(userName);
    }
}
