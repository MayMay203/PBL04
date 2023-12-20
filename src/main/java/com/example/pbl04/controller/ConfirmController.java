package com.example.pbl04.controller;

import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.entity.Thanhvien;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.MemberService;
import com.example.pbl04.service.RegisterService;
import com.example.pbl04.service.SessionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfirmController {
    private final ActivityService activityService;
    private final SessionService sessionService;
    private final RegisterService registerService;
    private final MemberService memberService;
    public ConfirmController(ActivityService activityService, SessionService sessionService, RegisterService registerService, MemberService memberService) {
        this.activityService = activityService;
        this.sessionService = sessionService;
        this.registerService = registerService;
        this.memberService = memberService;
    }
        @GetMapping("xet-duyet-hoat-dong")
        public String showConfirmActivity(Model model, HttpSession session)
        {
            sessionService.createSessionModel(model, session);
            Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
            if(myaccount!=null)
            {
                List<Hoatdong> listmyActivity = new ArrayList<>();
                List<Hoatdong> listmyAcitivityNeedConfirm = new ArrayList<>();
                List<Integer> countConfirm =new ArrayList<>();
                List<Integer> countConfirmed=new ArrayList<>();
                List<Hoatdong> listPost = new ArrayList<>();
                if(myaccount.getLoaiTK())
                {
                    listmyActivity = activityService.getAllMyPostNeedConfirm(myaccount.getId()); //Bao gồm hoạt động chưa xét duyệt, đã xét duyệt, đã huy.....
                    listmyAcitivityNeedConfirm = activityService.getAllMyActivityNeedConfirm(myaccount.getId());
                     countConfirm= activityService.countConfirm(listmyAcitivityNeedConfirm);
                     countConfirmed = activityService.countConfirmed(listmyAcitivityNeedConfirm);
                    listPost= activityService.getAllActiviyPost();
                    model.addAttribute("listPost",listPost);
                }
                else if(!myaccount.getLoaiTK()){
                    listmyActivity = activityService.getAllMyPostNeedConfirm(myaccount.getId());
                    listmyAcitivityNeedConfirm = activityService.getAllMyActivityNeedConfirm(myaccount.getId());
                    countConfirm= activityService.countConfirm(listmyAcitivityNeedConfirm);
                    countConfirmed = activityService.countConfirmed(listmyAcitivityNeedConfirm);
                }
                model.addAttribute("listmyActivity",listmyActivity);
                model.addAttribute("listmyAcitivityNeedConfirm",listmyAcitivityNeedConfirm);
                model.addAttribute("countConfirm",countConfirm);
                model.addAttribute("countConfirmed",countConfirmed);
            }
            return "XetDuyet";
        }
    @PostMapping(value="/Confirm-post")
    @ResponseBody
    public String confirmPost(@RequestParam("maHD") Integer maHD)
    {
        activityService.confirmActivityStage0(maHD);
        return "redirect:/XetDuyet";
    }
    @PostMapping(value="/Confirm-activity")
    @ResponseBody
    public String confirmActivity(@RequestParam("maHD") Integer maHD)
    {
        activityService.confirmActivityStage1(maHD);
        return "redirect:/XetDuyet";
    }
    @GetMapping("/get-member-need-confirm")
    @ResponseBody
    public ResponseEntity<List<Thanhvien>> viewMemberNeedConfirm(@RequestParam("maHD") Integer maHD)
    {
        try {
            List<Thanhvien> listMemberNeedConfirm = memberService.getMemberNeedConfirmByIDHD(maHD);
            System.out.println(listMemberNeedConfirm);
            return ResponseEntity.ok(listMemberNeedConfirm);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
   }
    @PostMapping("/Confirm-participants")
    @ResponseBody
    public void ConfirmParticipant(@RequestParam("maHD") Integer maHD,@RequestParam List<Integer> ids)
    {
        for(Integer maTK : ids)
        {
            memberService.ConfirmMember(maHD,maTK);
            System.out.println("ok");
        }
    }
    @GetMapping("/get-member-confirmed")
    @ResponseBody
    public ResponseEntity<List<Thanhvien>> viewMemberConfirmed(@RequestParam("maHD") Integer maHD)
    {
        try {
            List<Thanhvien> listMemberConfirmed = activityService.getMemberList(maHD);
            System.out.println(listMemberConfirmed);
            return ResponseEntity.ok(listMemberConfirmed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/Cancel-participants")
    @ResponseBody
    public void CancelParticipant(@RequestParam("maHD") Integer maHD,@RequestParam List<Integer> ids)
    {
        for(Integer maTK : ids)
        {
            memberService.CancelMember(maHD,maTK);
            System.out.println("ok");
        }
    }
    @PostMapping("/cancel-post")
    @ResponseBody
    public void CancelPost(@RequestParam("maHD") Integer maHD,@RequestParam String txtHuy)
    {
        activityService.CancelActivity(maHD,txtHuy);
    }

}
