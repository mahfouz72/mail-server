package com.mailserver.model.mail;

import com.mailserver.model.Attachment;
import com.mailserver.model.User;
import com.mailserver.service.AttachmentService;
import com.mailserver.service.DraftService;
import com.mailserver.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailSenderFacade {
    private UserService userService = UserService.getInstance();
    private DraftService draftService;
    private AttachmentService attachmentService;

    public MailSenderFacade(DraftService draftService,AttachmentService attachmentService) {
        this.draftService = draftService;
        this.attachmentService = attachmentService;
    }
    public  Mail sendMail(Mail mail,boolean wasDraft,List<String> attachmentIds){

        if(wasDraft){
            draftService.deleteDraft(mail.getFrom(),mail.getId());
        }

        mail.setDateTime(LocalDateTime.now().withNano(0));
        mail.setId(Long.toString(System.currentTimeMillis()));

        ArrayList<Attachment> attachments=new ArrayList<Attachment>();
        for (String attachmentId : attachmentIds) {
            attachments.add(attachmentService.get(attachmentId));
            attachmentService.remove(attachmentId);
        }
        mail.setAttachments(attachments);

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
