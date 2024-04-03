package com.example.demo.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


@Log4j2
@AllArgsConstructor
@Service
public class EmailSenderImpl implements EmailSender {
    private final JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}")
//     private String sender;
    @Override
    public void sendSimpleMail() throws MessagingException, IOException {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
////        mailMessage.setFrom(sender);
//        mailMessage.setTo("elhorafimed@gmail.com");
//        mailMessage.setSubject("Test Email");
//        String htmlContent = "<html><body><h1>Hello</h1><p>This is a test email to verify email connectivity.</p></body></html>";
//        mailMessage.setText(htmlContent);
//
//        //helper.setText(htmlContent, true);
//        javaMailSender.send(mailMessage);


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        //helper.setFrom(sender);
        helper.setTo("elhorafimed@gmail.com");
        helper.setSubject("Test Email");

        // Load HTML content from a template file or a string
        String htmlContent = loadHtmlTemplateFromFile("src/main/java/com/example/demo/email/EmailPassword.html");

        helper.setText(htmlContent, true); // Set HTML content to true

        javaMailSender.send(mimeMessage);
    };

    private String loadHtmlTemplateFromFile(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes, StandardCharsets.UTF_8);
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

