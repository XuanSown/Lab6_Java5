package com.example.lab6.service.impl;

import com.example.lab6.bean.MailInfo;
import com.example.lab6.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender sender;

    List<MailInfo> list = new ArrayList<>(); // Hàng đợi chứa mail

    @Override
    public void send(MailInfo mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        // Sử dụng Helper để thiết lập các thông tin
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());

        if(mail.getCc() != null && mail.getCc().length > 0) {
            helper.setCc(mail.getCc());
        }
        if(mail.getBcc() != null && mail.getBcc().length > 0) {
            helper.setBcc(mail.getBcc());
        }
        if(mail.getAttachments() != null && mail.getAttachments().length > 0) {
            for(String path: mail.getAttachments()) {
                File file = new File(path);
                helper.addAttachment(file.getName(), file);
            }
        }
        sender.send(message);
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body));
    }

    @Override
    public void queue(MailInfo mail) {
        list.add(mail);
    }

    @Override
    public void queue(String to, String subject, String body) {
        queue(new MailInfo(to, subject, body));
    }

    // Bài 2: Lên lịch gửi mail (mỗi 5 giây chạy 1 lần)
    @Scheduled(fixedDelay = 5000)
    public void run() {
        while(!list.isEmpty()) {
            MailInfo mail = list.remove(0);
            try {
                this.send(mail);
                System.out.println("Đã gửi mail tới: " + mail.getTo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }}
