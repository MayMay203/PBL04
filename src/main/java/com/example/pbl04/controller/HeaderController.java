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
    Taikhoan account= authenticationService.authenticateAccount(tenDN,matKhau);
//    Taikhoan account = accountService.CheckLogin(tenDN, matKhau);
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
        List<Chude> listTopics = topicService.getAllTopics();
        model.addAttribute("listTopics", listTopics);
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

//    -----------------đăng nhập---------------
@PostMapping("/dang-ky-tai-khoan")
public ResponseEntity<Map<String, Object>> addAccount(@RequestParam(name = "tenDN") String tenDN,
                                                       @RequestParam(name = "matKhau") String matKhau,
//                                                     @ModelAttribute(value = "account") Taikhoan taikhoan,
                                                       @ModelAttribute(value = "user") Thanhvien thanhvien,
//                                                       @RequestParam(name = "hoTen", required = false) String hoTen,
//                                                       @RequestParam(name = "ngaySinh", required = false) String ngaySinh,
//                                                       @RequestParam(name = "id", required = false) Integer id,
//                                                       @RequestParam(name = "id", required = false) Integer id,
//                                                       @RequestParam(name = "id", required = false) Integer id,
//                                                       @RequestParam(name = "id", required = false) Integer id,
//                                                       @RequestParam(name = "id", required = false) Integer id,
//                                                       @RequestParam(value = "imageInput", required = false) MultipartFile imageInput,
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
