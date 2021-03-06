package com.jacobgasser.jsmail;

import com.jacobgasser.jsmail.utils.pwd;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    private Properties emailProperties;
    private Session mailSession;
    private MimeMessage emailMessage;


    public void email(String message, String subject, String sendTo) {
        String emailPort = "587";
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        String toEmails = sendTo;
        String emailSubject = subject;
        String emailBody = message;
        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        try {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails));
            emailMessage.setSubject(emailSubject);
            emailMessage.setContent(emailBody, "text/html");
            String emailHost = "smtp.gmail.com";
            String fromUser = "jsmailbot";
            String fromUserEmailPassword = new pwd().getPassword();
            emailMessage.setFrom(fromUser);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserEmailPassword);
            // transport.connect(emailHost, fromUser, fromUserEmailPassword);
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
            System.out.println("Sent Email: " + "\n" + toEmails + "\n" + emailBody);
        } catch (MessagingException exception) {
            System.out.println("Error sending email, \n message: " + message);
            exception.printStackTrace();
        }
    }
}
