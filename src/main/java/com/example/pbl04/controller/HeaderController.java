package com.example.pbl04.controller;

import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.MemberService;
import com.example.pbl04.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HeaderController {
    public static final String SESSION_ATTR_ACCOUNT = "account";

    private final AccountService accountService;
    private final MemberService memberService;
    private final SessionService sessionService;
    @Autowired
    public HeaderController (AccountService accountService,
                             MemberService memberService, SessionService sessionService) {
        this.accountService = accountService;
        this.memberService = memberService;
        this.sessionService = sessionService;
    }
//    @GetMapping("/login")
//    public String showFormLogin(Model model){
//        model.addAttribute("account", new Taikhoan());
//        return  "Header";
//    }
//    @PostMapping("/check-login")
//    @ResponseBody
//    public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//
//        Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
//        if (account != null) {
//            session.setAttribute("accountUser", account);
//            response.put("account", account);
//            response.put("loginState", true);
//        } else {
//            response.put("error", "Tên đăng nhập hoặc mật khẩu không đúng");
//            session.setAttribute("accountUser",  new Taikhoan());
//            response.put("account", new Taikhoan());
//            response.put("loginState", false);
//        }
//
//        return response;
//    }


//    @GetMapping("/")
//    public String trangChu(HttpSession session, Model model) {
//        // Kiểm tra xem người dùng đã đăng nhập chưa
//        if (session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT) == null) {
//            // Người dùng chưa đăng nhập, có thể xử lý một số nội dung cho người dùng chưa đăng nhập ở đây
//        } else {
//            // Người dùng đã đăng nhập, lưu thông tin tài khoản vào model (nếu cần)
//            Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
//            model.addAttribute("account", account);
//        }
//
//        // Hiển thị trang chủ
//        return "Header";
//    }

//    @PostMapping("/check-login")
//@ResponseBody
//public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau, HttpSession session) {
//    Map<String, Object> response = new HashMap<>();
//
//    Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
//    if (account != null) {
//        // Đăng nhập thành công, lưu thông tin tài khoản vào session
//        session.setAttribute(SESSION_ATTR_ACCOUNT, account);
//        response.put("account", account);
//        response.put("loginState", true);
//    } else {
//        response.put("error", "Tên đăng nhập hoặc mật khẩu không đúng");
//        session.setAttribute(SESSION_ATTR_ACCOUNT, new Taikhoan());
//        response.put("account", new Taikhoan());
//        response.put("loginState", false);
//    }
//
//    return response;
//}
@PostMapping("/check-login")
@ResponseBody
public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau, HttpSession session) {
    Map<String, Object> response = new HashMap<>();

    Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
    if (account != null) {
        // Đăng nhập thành công, lưu thông tin tài khoản vào session
        session.setAttribute(SESSION_ATTR_ACCOUNT, account);
        response.put("account", account);
        response.put("loginState", true);
        response.put("isLogin", true);
        // Thêm mã JavaScript để load lại trang sau khi đăng nhập
        response.put("reloadPage", true);
    } else {
        response.put("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        session.setAttribute(SESSION_ATTR_ACCOUNT, new Taikhoan());
        response.put("account", new Taikhoan());
        response.put("loginState", false);
        response.put("isLogin", false);
    }

    return response;
}

    @GetMapping("/header")
    public String createSession(Model model, HttpSession session){
        sessionService.createSessionModel(model, session);
        return "Header";
    }

//    @PostMapping("/")
//    @ResponseBody
//    public Map<String, Object> islogin(@RequestBody Map<String, Boolean> requestBody, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        Boolean isLogin = requestBody.get("isLogin");
//        if (isLogin != null && isLogin) {
//            response.put("loginState", true);  // Thay đổi key thành "loginState"
//        } else {
//            response.put("loginState", false);  // Thay đổi key thành "loginState"
//        }
//        return response;
//    }

    @GetMapping("/logout")
    public @ResponseBody Map<String, Object> logout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        // Đăng xuất và làm sạch session
        session.invalidate();
        // Gửi JSON response
        response.put("success", true);
        return response;
    }



}
