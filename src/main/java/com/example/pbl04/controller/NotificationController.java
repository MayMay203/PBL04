package com.example.pbl04.controller;

import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.AccountService;
import com.example.pbl04.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.pbl04.entity.*;

import java.time.Instant;
import java.util.Date;
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
    //Them thong bao cho nguoi dung va ca admin(neu maTK null)
    @PostMapping("/them-thong-bao")
    @ResponseBody
    public ResponseEntity<Void> addNotification(@RequestParam(name="maTK",required = false) Integer maTK,
                                                @RequestParam("noiDung") String noiDung,
                                                @RequestParam("loaiTB") Integer loaiTB,
                                                @RequestParam("ma") Integer ma) {
        Thongbao tb = new Thongbao();
        tb.setNoiDung(noiDung);
        tb.setLoaiTB(loaiTB);
        tb.setMa(ma);
        tb.setTrangThai(false);
        tb.setThoiGianTB(Instant.now());
    //  Thêm thông bao cho nguoi dung
       if(maTK!=null){
           Taikhoan tk = accountService.getTaiKhoanByID(maTK);
           tb.setMaTK(tk);
           notificationService.addNotification(tb);
       }
    //  Thêm thông báo cho admin
        else{
            List<Taikhoan> adList = accountService.getAccountAd();
            for(Taikhoan acc : adList){
                tb.setMaTK(acc);
                notificationService.addNotification(tb);
           }
       }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @GetMapping("/nhan-danh-sach-thong-bao")
//    @ResponseBody
//    public ResponseEntity<List<Thongbao>> getNotificationList(HttpSession session){
//        Taikhoan acc = (Taikhoan)session.getAttribute("account");
//        List<Thongbao> notificationList = notificationService.getNotifiByIdAcc(acc.getId());
//        return ResponseEntity.ok(notificationList);
//    }

    @PostMapping("/cap-nhat-trang-thai-doc-thong-bao")
    @ResponseBody
    public ResponseEntity<Void> updateStatusOfNotice(@RequestParam("idList") List<Integer> idList){
        for(Integer id:idList){
            notificationService.updateStatusOfNotice(id);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}


