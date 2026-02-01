package com.example.lab6.service;

import com.example.lab6.bean.MailInfo;
import jakarta.mail.MessagingException;
import org.thymeleaf.standard.expression.MessageExpression;

public interface MailService {
    void send(MailInfo mail) throws MessagingException;
    void send(String to, String subject, String body) throws MessagingException;

    void queue(MailInfo mail);
    void queue(String to, String subject, String body);
}
