//package com.example.pbl04.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//    @Autowired
////    private JavaMailSender mailSender;
////
////    public void sendOtpEmail(String to, String subject, String body) {
////        SimpleMailMessage message = new SimpleMailMessage();
////        message.setFrom("hopefullyweb@gmail.com");
////        message.setTo(to);
////        message.setSubject(subject);
////        message.setText(body);
////
////        mailSender.send(message);
////
////        System.out.println("Mail send successfully.......");
////    }

//
//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    public void sendOtpEmail(String to, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//
//        javaMailSender.send(message);
//    }
//}
