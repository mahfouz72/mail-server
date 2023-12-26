package com.mailserver.service;

import com.mailserver.model.Folder;
import com.mailserver.model.User;
import com.mailserver.model.mail.Mail;
import com.mailserver.service.filter.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MailService {
    private UserService userService = UserService.getInstance();
    private FolderService folderService;

    public MailService(FolderService folderService) {
        this.folderService = folderService;
    }

    public List<Mail> getMails(String email, String folderName, String sortingCriteria) {
        List<Mail> mails = getMailsByFolderName(email,folderName);
        return sort(mails,sortingCriteria);
    }
    public List<Mail> getMailsByFolderName(String email,String folderName){
        User currentUser = userService.getUserByEmail(email);
        return switch (folderName.toLowerCase()){
            case "inbox" -> currentUser.getInbox();
            case "sent" -> currentUser.getSent();
            case "trash" -> currentUser.getTrash();
            case "drafts" -> currentUser.getDrafts();
            default -> folderService.getFolder(email,folderName).getMails();
        };
    }
    private List<Mail> sort(List<Mail> mails,String sortingCriteria){

        return switch (sortingCriteria.toLowerCase()){
            case "date" -> mails.stream().sorted(Comparator.comparing(Mail::getDateTime).reversed()).toList();
            case "priority" -> mails.stream().sorted(Comparator.comparing(Mail::getPriority).reversed()).toList();
            default -> mails;
        };
    }

    public List<Mail> deleteMail(String email, String folderName, String id) {

        User currentUser = userService.getUserByEmail(email);

        if(folderName.equals("trash")){
            currentUser.getTrash().removeIf(mail -> mail.getId().equals(id));
            return currentUser.getTrash();
        }

        return moveMail(email,folderName,"trash",id);
    }
    public List<Mail> moveMail(String email, String fromFolder, String toFolder, String id) {


        List<Mail> mails = getMailsByFolderName(email,fromFolder);

        Mail mailToBeMoved = mails.stream().filter(mail -> mail.getId().equals(id)).findFirst().orElse(null);
        mails.removeIf(mail -> mail.getId().equals(id));

        if(toFolder.equals("trash")){
            userService.getUserByEmail(email).getTrash().add(mailToBeMoved);
        }
        else{
            Folder to = folderService.getFolder(email,toFolder);
            to.getMails().add(mailToBeMoved);
        }
       return mails;
    }

    public List<Mail> filterMails(String email, String folderName, String filterCriteria, String filterValue) {

        List<Mail> mails = getMailsByFolderName(email,folderName);
        Filter filter;

        switch (filterCriteria.toLowerCase()){
            case "subject" -> filter = new SubjectFilter(filterValue);
            case "sender" -> filter = new SenderFilter(filterValue);
            case  "receiver" -> filter = new ReceiverFilter(filterValue);
            default -> filter = new BodyFilter(filterValue);
        }
        return filter.filter(mails);
    }
}
