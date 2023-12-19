package com.mailserver.controller;

import com.mailserver.model.User;
import com.mailserver.service.MailService;
import com.mailserver.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.mailserver.model.Mail;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public List<User>getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }


}
