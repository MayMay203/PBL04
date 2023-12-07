package com.example.pbl04.service;

import com.example.pbl04.controller.HeaderController;
import com.example.pbl04.dao.MemberRepository;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SessionService {

    private final MemberRepository memberRepository;

    public SessionService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createSessionModel(Model model, HttpSession session) {
        if (session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT) == null) {
            // Người dùng chưa đăng nhập, có thể xử lý một số nội dung cho người dùng chưa đăng nhập ở đây
            model.addAttribute("account", new Taikhoan());
            model.addAttribute("user", new Thanhvien());
            model.addAttribute("isLogin", false);
            // model.addAttribute("reloadPage", false);
        } else {
            // Người dùng đã đăng nhập, lưu thông tin tài khoản vào model (nếu cần)
            Taikhoan account = (Taikhoan) session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT);
            model.addAttribute("account", account);
            model.addAttribute("user", memberRepository.findMemberByID(account.getId()));
            model.addAttribute("isLogin", true);
            // model.addAttribute("reloadPage", true);
        }
        // model.addAttribute("isLogin", session.getAttribute(HeaderController.SESSION_ATTR_ACCOUNT) != null);
    }
}
