package com.mailserver.service;

import com.mailserver.model.Mail;
import com.mailserver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private UserService userService;

    public MailService(UserService userService) {
        this.userService = userService;
    }
    public List<Mail> getSent(String email){
        return userService.getUserByEmail(email).getSent();
    }
    public List<Mail> getInbox(String email){
        return userService.getUserByEmail(email).getInbox();
    }
    public Mail sendMail(Mail mail){

        String from = mail.getFrom();
        List<String> to = mail.getTo();
        for(String email:to){
            User receiver = userService.getUserByEmail(email);
            if(receiver != null){
                receiver.getInbox().add(mail);
            }
        }
        User sender = userService.getUserByEmail(from);
        if(sender != null){
            sender.getSent().add(mail);
        }
       return mail;
    }
}
