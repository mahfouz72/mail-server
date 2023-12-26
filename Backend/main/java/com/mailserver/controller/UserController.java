package com.mailserver.controller;

import com.mailserver.model.User;
import com.mailserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private  UserService userService = UserService.getInstance();


    @PostMapping("/user/register")
    public User register(@Valid @RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/user/login")
    public boolean logIn(@RequestBody User user){
          return userService.logIn(user.getEmail(),user.getUserName());
    }

    @GetMapping("/user")
    public List<User>getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/user/save")
    public void saveUsers(){
        userService.saveUsers();
    }
    @GetMapping("/user/load")
    public void loadUsers(){
        userService.LoadUsers();
    }

}
