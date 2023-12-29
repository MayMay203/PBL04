package com.example.pbl04.controller;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class EmailController {

    private final JavaMailSender javaMailSender;
    private final AccountService accountService;
    private final PasswordEncoderService passwordEncoderService;
    @Autowired
    public EmailController(JavaMailSender javaMailSender, AccountService accountService, PasswordEncoderService passwordEncoderService) {
        this.javaMailSender = javaMailSender;
        this.accountService = accountService;
        this.passwordEncoderService = passwordEncoderService;
    }


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
    public Map<String, Object> sendOTP(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Đã vô Controller");
        // Tạo OTP
        String otp = generateOtp();
        // Gửi OTP đến email
        sendOtpEmail(email, otp);
        response.put("otp", otp);
        response.put("email", email);
        // Gửi JSON response
        response.put("success", "OTP sent successfully!");

        return response;
    }

    @PostMapping("/confirm-otp")
    @ResponseBody
    public Map<String, Object> confirmOTP(@RequestParam String otp_email, @RequestParam String otp_input, @RequestParam String email) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Đã vô Controller" + email + "---" + otp_email + "------" + otp_input);
        if(otp_email.equals(otp_input)){
            System.out.println("Đúng otp");
            Thanhvien thanhvien = accountService.findMemberByEmail(email);
            String newPass = "TK1000" + thanhvien.getMaTK().getId().toString();
            String encodePass = passwordEncoderService.encodePassword(newPass);
              accountService.changePasswordByOTP(thanhvien.getMaTK(), encodePass);
            response.put("success", "Mật khẩu của bạn đã được reset\n Mật khẩu mới:" + newPass);
        }
        else{
            System.out.println("Sai otp");
            response.put("error", "OTP xác nhận không đúng!");
        }
        // Gửi JSON response


        return response;
    }
}


