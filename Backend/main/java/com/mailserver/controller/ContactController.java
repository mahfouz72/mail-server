package com.mailserver.controller;

import com.mailserver.model.Contact;
import com.mailserver.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    private ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService=contactService;
    }

    //Create contact
    @PostMapping("/contact/create/{userEmailAddress}")
    public void createContact(@PathVariable String userEmailAddress,@RequestBody Contact contact){
        contactService.createContact(userEmailAddress,contact);
    }
    //edit contact
    @PostMapping("/contact/edit/{userEmailAddress}/{contactName}/{contactEmailAddress}")
    public void editContact(@PathVariable String userEmailAddress,@PathVariable String contactName,
                              @PathVariable String contactEmailAddress){
        contactService.editContact(userEmailAddress,contactName,contactEmailAddress);
    }
    //delete contact
    @DeleteMapping("/contact/delete/{userEmailAddress}/{contactEmailAddress}")
    public void deleteContact(@PathVariable String userEmailAddress,@PathVariable String contactEmailAddress){
        contactService.deleteContact(userEmailAddress,contactEmailAddress);
    }
    @GetMapping("/contact/sort/{userEmailAddress}")
    public List<Contact> sortContacts(@PathVariable String userEmailAddress){
        return contactService.sortContacts(userEmailAddress);
    }
}
