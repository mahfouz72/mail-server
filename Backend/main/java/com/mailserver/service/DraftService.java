package com.mailserver.service;

import com.mailserver.model.Attachment;
import com.mailserver.model.mail.Mail;
import com.mailserver.model.mail.MailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DraftService {
    private UserService userService = UserService.getInstance();
    @Autowired
    private AttachmentService attachmentService;
    public Mail addDraft(String email, Mail mail, ArrayList<String> attachmentIds) {
        mail.setId(Long.toString(System.currentTimeMillis()));
        mail.setDateTime(LocalDateTime.now().withNano(0));
        ArrayList<Attachment> attachments=new ArrayList<Attachment>();
        for (String attachmentId : attachmentIds) {
            attachments.add(attachmentService.get(attachmentId));
            attachmentService.remove(attachmentId);
        }
        mail.setAttachments(attachments);
        userService.getUserByEmail(email).getDrafts().add(mail);
        return mail;
    }

    public List<Mail> updateDraft(String email, String id, Mail newDraft) {

        List<Mail> userDrafts = userService.getUserByEmail(email).getDrafts();

        Mail draft = userDrafts.stream()
                .filter(mail -> mail.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (draft != null) {
            Mail updatedDraft = new MailBuilder()
                    .setId(id)
                    .setFrom(newDraft.getFrom())
                    .setTo(newDraft.getTo())
                    .setSubject(newDraft.getSubject())
                    .setAttachments(newDraft.getAttachments())
                    .setBody(newDraft.getBody())
                    .setDateTime(LocalDateTime.now().withNano(0))
                    .setPriority(newDraft.getPriority())
                    .build();

            userDrafts.removeIf(mail -> mail.getId().equals(id));
            userDrafts.add(updatedDraft);
        }

        return userService.getUserByEmail(email).getDrafts();
    }

    public List<Mail> deleteDraft(String email, String id) {
        userService.getUserByEmail(email).getDrafts().removeIf(mail -> mail.getId().equals(id));
        return userService.getUserByEmail(email).getDrafts();
    }

    public List<Mail> getDrafts(String email) {
        return userService.getUserByEmail(email).getDrafts();
    }
}
