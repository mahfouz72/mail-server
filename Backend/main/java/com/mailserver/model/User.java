package com.mailserver.model;

import com.mailserver.model.mail.Mail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class User {
    private String userName;
    private String email;
    private String password;
    private List<Mail> sent;
    private List<Mail> inbox;
    private List<User> contacts;
    //TODO: trash,folders,contacts

    public User() {
        this.sent = new ArrayList<>();
        this.inbox = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    public User(String userName, String email, List<Mail> sent, List<Mail> inbox, List<User> contacts) {
        this.userName = userName;
        this.email = email;
        this.sent = sent;
        this.inbox = inbox;
        this.contacts = contacts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public List<Mail> getSent() {
        return sent;
    }

    public void setSent(List<Mail> sent) {
        this.sent = sent;
    }

    public List<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(List<Mail> inbox) {
        this.inbox = inbox;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
