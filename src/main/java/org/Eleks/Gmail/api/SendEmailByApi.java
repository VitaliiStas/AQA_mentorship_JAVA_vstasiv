package org.Eleks.Gmail.api;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailByApi {
    private String userName = "emat23024@gmail.com";
    private String password = "Passw0rd1234";
    private String sendFrom = "emat23024@gmail.com";
    private String smtp = "smtp.gmail.com";

    protected static List<String> sendToListOrCC = Arrays.asList(
            "tt8397519@gmail.com"
            , "tt8397519+1@gmail.com"
            , "tt8397519+2@gmail.com"
            , "tt8397519+3@gmail.com");

    // Get system properties
    private Properties properties = System.getProperties();
    // Get the Session object.// and pass username and password
    private Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(getUserName(), getPassword());
        }

    });
    // Create a default MimeMessage object.
    private MimeMessage message = new MimeMessage(session);

    public SendEmailByApi() {
    }

    public Properties getProperties() {
        return properties;
    }

    public Session getSession() {
        return session;
    }

    public MimeMessage getMessage() {
        return message;
    }


    public String getSmtp() {
        return smtp;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void sendEmailByApi(String sendTo
            , String emailSubject
            , String emailMessage) {

        // Setup mail server
        getProperties().put("mail.smtp.host", getSmtp());
        getProperties().put("mail.smtp.port", "465");
        getProperties().put("mail.smtp.ssl.enable", "true");
        getProperties().put("mail.smtp.auth", "true");

        // Used to debug SMTP issues
        getSession().setDebug(true);

        try {
            // Set From: header field of the header.
            getMessage().setFrom(new InternetAddress(getSendFrom()));

            // Set To: header field of the header.
            getMessage().addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo));
            sendToListOrCC.stream().forEach(e -> {
                try {
                    getMessage().addRecipient(Message.RecipientType.CC, new InternetAddress(e));
                } catch (MessagingException messagingException) {
                    messagingException.printStackTrace();
                }
            });


            // Set Subject: header field
            getMessage().setSubject(emailSubject);

            // Now set the actual message
            getMessage().setText(emailMessage);

            System.out.println("sending...");
            // Send message
            Transport.send(getMessage());
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
