package fr.dawan.demospringmvc.services;

public interface IEmailService {
    void sendEmail(String subject, String text, String recipient) throws Exception;
}
