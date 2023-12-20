package com.mailserver.controller;

import com.mailserver.model.mail.Mail;
import com.mailserver.model.mail.MailSenderFacade;
import com.mailserver.service.MailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {
    private MailService mailService;
    private MailSenderFacade mailSenderFacade;

    public MailController(MailService mailService,MailSenderFacade mailSenderFacade) {
        this.mailService = mailService;
        this.mailSenderFacade = mailSenderFacade;
    }

    @GetMapping("{email}/sent")
    public List<Mail> getSent(@PathVariable String email){
        return mailService.getSent(email);
    }

    @GetMapping("{email}/inbox")
    public List<Mail> getInbox(@PathVariable String email,@RequestParam(required = false,defaultValue = "date") String sortingCriteria){
        return mailService.getInbox(email,sortingCriteria);
    }

    @PostMapping("/compose")
    public Mail sendMail(@RequestBody Mail mail, @RequestParam String priority){
        return mailSenderFacade.sendMail(mail,priority);
    }
}
