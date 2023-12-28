//package com.example.pbl04.service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import java.util.Random;
//
//@Service
//public class OtpService {
//
//    private final JavaMailSender javaMailSender;
//
//    @Autowired
//    public OtpService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    public String generateOtp() {
//        Random random = new Random();
//        StringBuilder otp = new StringBuilder();
//        for (int i = 0; i < 6; i++) { // Đặt độ dài OTP là 6 số
//            otp.append(random.nextInt(10));
//        }
//        return otp.toString();
//    }
//
//    public void sendOtpEmail(String email, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Cấp lại mật khẩu trên HOPEFULLY");
//        message.setText("Your OTP code is: " + otp);
//        javaMailSender.send(message);
//    }
//}
