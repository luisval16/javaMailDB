/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.entity.Registro;
import com.jpacontroller.RegistroJpaController;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dell
 */
public class MailController {
    
    public void probar(Registro reg) throws Exception{
        RegistroJpaController jpa = new RegistroJpaController(Persistence.createEntityManagerFactory("mailPU"));
        jpa.create(reg);
    }
    
    public void enviar(){
    Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("hayteguachoprueba", "orasoft123");
                }
                });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hayteguachoprueba@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, 
                    InternetAddress.parse("eduardoc1112@gmail.com"));
            message.setSubject("Prueba Java Mail");
            message.setText("Dear Mail Subject:"
                    + "\n\n No spam yo my email, please!");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    
    }
}
