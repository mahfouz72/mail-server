package com.mailserver.service;

import com.mailserver.model.Contact;
import com.mailserver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    //Assuming that the contact is already a user so no need to have a collection for contacts
    private UserService userService = UserService.getInstance();

    private boolean checkContactExistence(List<String> emailAddresses){
        for(String emailAddress:emailAddresses){
            if(userService.getUserByEmail(emailAddress) == null){
                return false;
            }
        }
        return true;
    }
    public void createContact(String userEmailAddress,Contact contact){
        if(checkContactExistence(contact.getEmailAddresses())){
            userService.getUserByEmail(userEmailAddress).getContacts().add(contact);
        }
    }
    public void editContact(String userEmailAddress,String contactName,String contactEmailAddress){
            Contact contact = userService.getUserByEmail(userEmailAddress).getContacts()
                    .stream().filter(c -> c.getEmailAddresses()
                            .contains(contactEmailAddress)).findFirst().get();
            contact.setName(contactName);
    }
    public void deleteContact(String userEmailAddress,String contactEmailAddress){
        List<Contact> contacts = userService.getUserByEmail(userEmailAddress).getContacts();
        contacts.removeIf(contact -> contact.getEmailAddresses().contains(contactEmailAddress));
    }
    public List<Contact> sortContacts(String userEmailAddress){
       return userService.getUserByEmail(userEmailAddress).getContacts()
               .stream().sorted(Contact::compareTo).toList();
    }

}