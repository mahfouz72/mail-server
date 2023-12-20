package com.mailserver.model.mail;

import com.mailserver.model.User;
import com.mailserver.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailSenderFacade {
    private UserService userService;
    public MailSenderFacade(UserService userService){
        this.userService = userService;
    }
    public  Mail sendMail(Mail mail, String priority){

        switch (priority.toLowerCase()) {
            case "low" -> mail.setPriority(Priority.LOW);
            case "medium" -> mail.setPriority(Priority.MEDIUM);
            case "high" -> mail.setPriority(Priority.HIGH);
            case "urgent" -> mail.setPriority(Priority.URGENT);
        }

        mail.setDateTime(LocalDateTime.now().withSecond(0).withNano(0));

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
