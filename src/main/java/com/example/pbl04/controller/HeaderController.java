package com.example.pbl04.controller;
import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HeaderController {
    public static final String SESSION_ATTR_ACCOUNT = "account";
    private final TopicService topicService;
    private final AccountService accountService;
    private final MemberService memberService;
    private final SessionService sessionService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoderService passwordEncoderService;
    @Autowired
    public HeaderController (TopicService topicService, AccountService accountService,
                             MemberService memberService, SessionService sessionService, AuthenticationService authenticationService, PasswordEncoderService passwordEncoderService) {
        this.topicService = topicService;
        this.accountService = accountService;
        this.memberService = memberService;
        this.sessionService = sessionService;
        this.authenticationService = authenticationService;
        this.passwordEncoderService = passwordEncoderService;
    }
    @GetMapping("/footer")
    public String show(Model model, HttpSession session){
        sessionService.createSessionModel(model, session);
        return "Footer";
    }

@PostMapping("/check-login")
@ResponseBody
public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau, HttpSession session) {
    Map<String, Object> response = new HashMap<>();
    Taikhoan account= authenticationService.authenticateAccount(tenDN,matKhau);
//    Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
    if (account != null) {
        System.out.println("THANH CONG");
        // Đăng nhập thành công, lưu thông tin tài khoản vào session
        session.setAttribute(SESSION_ATTR_ACCOUNT, account);
        response.put("account", account);
        response.put("loginState", true);
        response.put("isLogin", true);
        // Thêm mã JavaScript để load lại trang sau khi đăng nhập
        response.put("reloadPage", true);
    } else {
        System.out.println("Khong co tai khoan");
        response.put("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        session.setAttribute(SESSION_ATTR_ACCOUNT, new Taikhoan());
        response.put("account", new Taikhoan());
        response.put("loginState", false);
        response.put("isLogin", false);
    }
    return response;
}
@PostMapping("/change-password")
@ResponseBody
public Map<String, Object> login(@RequestParam String tenDN, @RequestParam String matKhau,@RequestParam String matKhauMoi, HttpSession session) {
    Map<String, Object> response = new HashMap<>();

    Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
    System.out.println("Chạy Controller");
    if (account != null) {
        {
            account.setMatKhau(matKhauMoi);
            account.setId(account.getId());
            accountService.changePassword(account);
        }
        // Thêm mã JavaScript để load lại trang sau khi đăng nhập
        response.put("reloadPage", true);
    } else {
        response.put("error", "Tên đăng nhập hoặc mật khẩu cũ không đúng");
    }

    return response;
}

    @GetMapping("/header")
    public String createSession(Model model, HttpSession session){
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("listTopics", listTopics);
        sessionService.createSessionModel(model, session);
        return "Header";
    }


    @GetMapping("/logout")
    public @ResponseBody Map<String, Object> logout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        // Đăng xuất và làm sạch session
        session.invalidate();
        // Gửi JSON response
        response.put("success", true);
        return response;
    }

//    -----------------đăng nhập---------------
@PostMapping("/dang-ky-tai-khoan")
public ResponseEntity<Map<String, Object>> addAccount(@RequestParam(name = "tenDN") String tenDN,
                                                       @RequestParam(name = "matKhau") String matKhau,
                                                       @ModelAttribute(value = "user") Thanhvien thanhvien,
                                                       RedirectAttributes redirectAttributes,
                                                       HttpSession session, Model model) throws IOException, InterruptedException {

    Map<String, Object> response = new HashMap<>();
    System.out.println("Da vao controller");
    Taikhoan taikhoan = new Taikhoan();
    String anhDaiDien = "/images/avatar-mac-dinh.png";
    taikhoan.setAnhDaiDien(anhDaiDien);
    String md5matkhau = passwordEncoderService.encodePassword(matKhau);
    taikhoan.setMatKhau(md5matkhau);
    taikhoan.setTenDN(tenDN);
    taikhoan.setLoaiTK(false);
    System.out.println("Account:" + taikhoan.getMatKhau() + taikhoan.getTenDN() + taikhoan.getLoaiTK());
    accountService.addAccount(taikhoan);
//    Thanhvien thanhvien = new Thanhvien();
    thanhvien.setMaTK(taikhoan);
    System.out.println("User:" + thanhvien.getMaTK().getId() + thanhvien.getHoTen() + thanhvien.getEmail() + thanhvien.getHoTen()+thanhvien.getDiaChi()+thanhvien.getNgaySinh()+thanhvien.getSoDienThoai());
    memberService.addMember(thanhvien);

    response.put("message", "Thông báo: Thông tin đã được cập nhật thành công!");
    response.put("success", true);
    return ResponseEntity.ok(response);
}


}
