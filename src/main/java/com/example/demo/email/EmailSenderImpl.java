package com.example.demo.email;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Log4j2
@AllArgsConstructor
@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
    @Override
    public boolean sendSimpleMail() {
        System.out.println("test 1");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("elhorafimed@gmail.com");
        mailMessage.setSubject("Test Email");
        mailMessage.setText("This is a test email to verify email connectivity.");
        System.out.println("test 2");

        try {
            System.out.println("test 3");

            javaMailSender.send(mailMessage);
            return true; // Email sent successfully
        } catch (MailException e) {
            System.out.println("test 4");

            e.printStackTrace(); // Log the exception
            return false; // Failed to send email
        }
    }
//    private final JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.username}")
//    private String sender;
//
//    public void sendSimpleMail(EmailDetails details) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(sender);
//        mailMessage.setTo(details.getRecipient());
//        mailMessage.setText(details.getMsgBody());
//        mailMessage.setSubject(details.getSubject());
//        javaMailSender.send(mailMessage);
//    }

    // Method 2
    // To send an email with attachment
//    public String sendMailWithAttachment(EmailDetails details) {
//        // Creating a mime message
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException e) {
//
//            // Display message when exception occurred
//            return "Error while sending mail!!!";
//        }
//    }
}

