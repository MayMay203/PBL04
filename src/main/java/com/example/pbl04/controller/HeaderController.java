package com.example.pbl04.controller;

import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HeaderController {

    private final AccountService accountService;
    @Autowired
    public HeaderController (AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/login")
    public String showFormLogin(Model model){
        model.addAttribute("account", new Taikhoan());
        return  "Header";
    }

    //    @RequestMapping(value = "/check-login", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("/check-login")
//    public String login(Model model, @RequestParam String tenDN, @RequestParam String matKhau)
//    {
//        Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
//        if (account != null) {
//            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu đúng");
//            model.addAttribute("account", account);
//            model.addAttribute("loginState", true);
//            return "TrangChu";
//        } else {
//            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
//            model.addAttribute("account", new Taikhoan());
//            model.addAttribute("loginState", false);
//            return  "TrangChu";
//        }
//    }
    @ResponseBody
    public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau) {
        Map<String, Object> response = new HashMap<>();

        Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
        if (account != null) {
            response.put("error", "Tên đăng nhập hoặc mật khẩu đúng");
            response.put("account", account);
            response.put("loginState", true);
        } else {
            response.put("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            response.put("account", new Taikhoan());
            response.put("loginState", false);
        }

        return response;
    }
//    @GetMapping
//        @PostMapping("/check-login")
//    public String login(Model model, @RequestParam String username, @RequestParam String password) {
//        if(accountService.CheckLogin(username,password) != null){
//            model.addAttribute("account", new Taikhoan());
//            return "Header";
//        }
//        else{
//            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
//            model.addAttribute("account", new Taikhoan());
//            return "Header";
//        }
//    }
}