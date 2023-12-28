package com.example.pbl04.controller;
import com.example.pbl04.entity.Taikhoan;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class OtpController {
    @Autowired
    private JavaMailSender javaMailSender;
    public String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) { // Đặt độ dài OTP là 6 số
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Cấp lại mật khẩu trên HOPEFULLY");
        message.setText("Your OTP code is: " + otp);
        javaMailSender.send(message);
    }

    @PostMapping("/send-otp")
    @ResponseBody
    public  Map<String, Object> sendOtp(@RequestParam(name = "email") String email) {
        System.out.println("Đã vô Controller");
        // Tạo OTP
        String otp = generateOtp();
        // Gửi OTP đến email
        sendOtpEmail(email, otp);
        Map<String, Object> response = new HashMap<>();
        // Gửi JSON response
        response.put("success", "OTP sent successfully!");
        return response;
    }

    @PostMapping("/send-otp")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Đã vô Controller");
        // Tạo OTP
        String otp = generateOtp();
        // Gửi OTP đến email
        sendOtpEmail(email, otp);
        // Gửi JSON response
        response.put("success", "OTP sent successfully!");

        return response;
    }
}


