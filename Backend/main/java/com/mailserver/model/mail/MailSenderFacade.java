package com.mailserver.model.mail;

import com.mailserver.model.User;
import com.mailserver.service.DraftService;
import com.mailserver.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailSenderFacade {
    private UserService userService = UserService.getInstance();
    private DraftService draftService;

    public MailSenderFacade(DraftService draftService) {
        this.draftService = draftService;
    }
    public  Mail sendMail(Mail mail,boolean wasDraft){

        if(wasDraft){
            draftService.deleteDraft(mail.getFrom(),mail.getId());
        }

        mail.setDateTime(LocalDateTime.now().withNano(0));
        mail.setId(Long.toString(System.currentTimeMillis()));

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
