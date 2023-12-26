package com.mailserver.service;

import com.mailserver.model.mail.Mail;
import com.mailserver.model.mail.MailBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DraftService {
    private UserService userService = UserService.getInstance();

    public Mail addDraft(String email, Mail mail) {
        mail.setId(Long.toString(System.currentTimeMillis()));
        mail.setDateTime(LocalDateTime.now().withNano(0));
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
