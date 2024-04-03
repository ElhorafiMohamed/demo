package com.example.demo.email;


import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailSender {

    void sendSimpleMail() throws MessagingException, IOException;
   // String sendMailWithAttachment(EmailDetails details);
}
