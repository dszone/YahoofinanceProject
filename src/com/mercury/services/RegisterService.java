package com.mercury.services;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mercury.daos.*;
import com.mercury.beans.*;

@Service
@Transactional
public class RegisterService {
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void register(User user){
		userDao.addUser(user);
	}
	
	public void sendMail(String username, String email) {
		final String fromMail = "m.yahoof201506@gmail.com";
		final String password = "mercury201506";
		
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";  
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");   
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);   
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");   
        prop.setProperty("mail.smtp.port", "465");   
        prop.setProperty("mail.smtp.socketFactory.port", "465");   
        prop.put("mail.smtp.auth", "true");   
        
        
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() { 
		return new PasswordAuthentication(fromMail, password); 		} 
            });
        try {		
        	//create a default MimeMessage object
            Message msg = new MimeMessage(session);
            //set From: header field of the header
            msg.setFrom(new InternetAddress(fromMail));
            //set To: header field of the header
            //msg.setRecipient(Message.RecipientType.TO, new InternetAddress("good.qu@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //msg.addRecipient(Message.RecipientType.CC, "good.qu@gmail.com");
            //set Subject: header field
            msg.setSubject("Greetings from Yahoo Finance Trading System"); msg.setSentDate(new Date());
            //msg.setText("Hello!"); 
            
        
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            //now set the actual message
            messageBodyPart.setText("Dear " + username +",\n\nWelcome to Yahoo Finance!");
            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            //set text message part
            multipart.addBodyPart(messageBodyPart);
        
            // Part two is attachment
            /*messageBodyPart = new MimeBodyPart();
            String filename = "D:\\Mercury systems Inc\\status reports\\status_20150616.doc";
            FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);*/
            
            //send the complete message parts
            msg.setContent(multipart);
            //send message
            Transport.send(msg);
            System.out.println("Message sent successfully!");
        } catch (Exception e) { 
        	System.out.println(e); 
        }
	}
}
