package com.example.pbl04.controller;
import com.example.pbl04.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtpController {
    private final OtpService otpService;
    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        String otp = otpService.generateOtp();
        otpService.sendOtpEmail(email, otp);
        return "OTP sent successfully.";
    }

}

