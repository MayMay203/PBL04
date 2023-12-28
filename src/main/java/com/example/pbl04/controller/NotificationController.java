package com.example.pbl04.controller;

import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thongbao;
import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.NotificationService;
import com.example.pbl04.service.SuggestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NotificationController {
    private final NotificationService notificationService;
    private final AccountService accountService;
    @Autowired
    public NotificationController(NotificationService notificationService, AccountService accountService){
        this.notificationService= notificationService;
        this.accountService = accountService;
    }
    @PostMapping("/them-thong-bao-de-xuat")
    @ResponseBody
    public ResponseEntity<Void> addNoticeOfSugg(@RequestParam("maTK") Integer maTK,
                                                @RequestParam("noiDung") String noiDung,
                                                @RequestParam("loaiTB") Integer loaiTB,
                                                @RequestParam("ma") Integer ma) {
        Thongbao tb = new Thongbao();
        Taikhoan tk = accountService.getTaiKhoanByID(maTK);
        tb.setMaTK(tk);
        tb.setNoiDung(noiDung);
        tb.setLoaiTB(loaiTB);
        tb.setMa(ma);
        notificationService.addNoticeOfSugg(tb);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/nhan-danh-sach-thong-bao")
    @ResponseBody
    public ResponseEntity<List<Thongbao>> getNotificationList(HttpSession session){
        Taikhoan acc = (Taikhoan)session.getAttribute("account");
        List<Thongbao> notificationList = notificationService.getNotifiByIdAcc(acc.getId());
        return ResponseEntity.ok(notificationList);
    }

}


