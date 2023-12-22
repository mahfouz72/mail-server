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

    @GetMapping("{email}/{folderName}")
    public List<Mail> getMails(@PathVariable String email,@PathVariable String folderName,
                               @RequestParam(required = false,defaultValue = "date") String sortingCriteria){

            return mailService.getMails(email,folderName,sortingCriteria);
    }

    @PostMapping("/compose")
    public Mail sendMail(@RequestBody Mail mail, @RequestParam String priority){
        return mailSenderFacade.sendMail(mail,priority);
    }
    @PostMapping("{email}/{fromFolder}/{toFolder}/{id}")
    public List<Mail> moveMail(@PathVariable String email,@PathVariable String fromFolder,
                               @PathVariable String toFolder,@PathVariable String id){

        return mailService.moveMail(email,fromFolder,toFolder,id);
    }

    @DeleteMapping("{email}/{folderName}/{id}")
    public List<Mail> deleteMail(@PathVariable String email,@PathVariable String folderName,@PathVariable String id){
        return mailService.deleteMail(email,folderName,id);
    }

}
