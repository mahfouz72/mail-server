package com.mailserver.controller;

import com.mailserver.model.mail.Mail;
import com.mailserver.model.mail.MailSenderFacade;
import com.mailserver.service.DraftService;
import com.mailserver.service.MailService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MailController {
    private MailService mailService;
    private MailSenderFacade mailSenderFacade;
    private DraftService draftService;

    public MailController(MailService mailService,MailSenderFacade mailSenderFacade,DraftService draftService) {
        this.mailService = mailService;
        this.mailSenderFacade = mailSenderFacade;
        this.draftService = draftService;
    }

    @GetMapping("{email}/{folderName}")
    public List<Mail> getMails(@PathVariable String email,@PathVariable String folderName,
                               @RequestParam(required = false,defaultValue = "date") String sortingCriteria){

            return mailService.getMails(email,folderName,sortingCriteria);
    }

    @GetMapping("{email}/{folderName}/filter")
    public List<Mail> filterMails(@PathVariable String email,@PathVariable String folderName,
                                  @RequestParam String filterCriteria,@RequestParam String filterValue){

        return mailService.filterMails(email,folderName,filterCriteria,filterValue);
    }

    @PostMapping("/compose")
    public Mail sendMail(@RequestBody Mail mail, @RequestParam(required = false) boolean wasDraft,
                         @RequestParam ArrayList<String> attachmentIds){
        return mailSenderFacade.sendMail(mail,wasDraft,attachmentIds);
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

    @PostMapping("/createDraft/{email}")
    public Mail createDraft(@PathVariable String email,@RequestBody Mail mail){
        return draftService.addDraft(email,mail);
    }
    @PostMapping("/updateDraft/{email}/{id}")
    public List<Mail> updateDraft(@PathVariable String email,@PathVariable String id,@RequestBody Mail newDraft){
        return draftService.updateDraft(email,id,newDraft);
    }
    @DeleteMapping("/deleteDraft/{email}/{id}")
    public List<Mail> deleteDraft(@PathVariable String email,@PathVariable String id){
        return draftService.deleteDraft(email,id);
    }

}
