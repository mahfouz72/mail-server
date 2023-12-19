package com.mailserver.controller;

import com.mailserver.model.Mail;
import com.mailserver.service.MailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    private MailService mailService;
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("{email}/sent")
    public List<Mail> getSent(@PathVariable String email){
        return mailService.getSent(email);
    }

    @GetMapping("{email}/inbox")
    public List<Mail> getInbox(@PathVariable String email){
        return mailService.getInbox(email);
    }

    @PostMapping("/send")
    public Mail sendMail(@RequestBody Mail mail){
        return mailService.sendMail(mail);
    }
}
