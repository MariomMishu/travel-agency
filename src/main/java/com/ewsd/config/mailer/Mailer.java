package com.ewsd.config.mailer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.ewsd.config.persistence.HibernateConfig;

public class Mailer {

    protected static Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = Mailer.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    public static void sendMail(String to, String subject, String content) {
        java.util.Properties props = getBuiltProperties("mailer.properties");


        Session session = Session.getDefaultInstance(props, new Authenticator() {
		
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teamg5.bit@gmail.com", "bit12345");

            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Mainul"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            System.out.println("Message sent successfully!");
        } catch (AuthenticationFailedException e) {
        	Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, e.getMessage());
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
