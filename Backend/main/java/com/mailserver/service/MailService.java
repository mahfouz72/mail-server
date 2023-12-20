package com.mailserver.service;

import com.mailserver.model.mail.Mail;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public List<Mail> getInbox(String email,String sortingCriteria){
        List<Mail> inbox = userService.getUserByEmail(email).getInbox();
        return sort(inbox,sortingCriteria);
    }
    private List<Mail> sort(List<Mail> mails,String sortingCriteria){

        return switch (sortingCriteria.toLowerCase()){
            case "date" -> mails.stream().sorted(Comparator.comparing(Mail::getDateTime).reversed()).toList();
            case "priority" -> mails.stream().sorted(Comparator.comparing(Mail::getPriority).reversed()).toList();
            default -> mails;
        };
    }
    //TODO: add mails to specific folder
    //TODO: get mails from specific folder
    //TODO: delete mails from specific folder and add to trash

}
