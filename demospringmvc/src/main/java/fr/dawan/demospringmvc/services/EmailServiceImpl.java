package fr.dawan.demospringmvc.services;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.properties.mail.from}") //param définit dans application.properties
    private String from;

    @Override
    public void sendEmail(String subject, String text, String recipient) throws Exception {

        MimeMessage m = emailSender.createMimeMessage();
        m.setFrom(from);
        m.setRecipients(Message.RecipientType.TO, recipient);
        m.setSubject(subject);
        m.setText(text,"utf-8","text");
        emailSender.send(m);


    }
}
