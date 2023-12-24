package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConfirmController {
    private final ActivityService activityService;
    private final SessionService sessionService;
    private final RegisterService registerService;
    private final MemberService memberService;
    private final SuggestionService suggestionService;
    @Autowired
    public ConfirmController(ActivityService activityService, SessionService sessionService, RegisterService registerService, MemberService memberService,SuggestionService suggestionService) {
        this.activityService = activityService;
        this.sessionService = sessionService;
        this.registerService = registerService;
        this.memberService = memberService;
        this.suggestionService = suggestionService;
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
                List<Hoatdong> listCancelActivity= new ArrayList<>();
                List<Dangky> listActivityParticipate = new ArrayList<>();
                List<Dexuat> suggestionList = new ArrayList<>();

                if(myaccount.getLoaiTK())
                {
                    listmyActivity = activityService.getAllMyPostNeedConfirm(myaccount.getId()); //Bao gồm hoạt động chưa xét duyệt, đã xét duyệt, đã huy.....
                    listmyAcitivityNeedConfirm = activityService.getAllMyActivityNeedConfirm(myaccount.getId());
                     countConfirm= activityService.countConfirm(listmyAcitivityNeedConfirm);
                     countConfirmed = activityService.countConfirmed(listmyAcitivityNeedConfirm);
                    listPost= activityService.getAllActiviyPost();
                    listCancelActivity = activityService.getListCancel();
                    listActivityParticipate = activityService.getListActivityParticipate(myaccount.getId());
                    suggestionList = suggestionService.getSuggestionNConf();
                    model.addAttribute("listPost",listPost);
                    model.addAttribute("suggestionList",suggestionList);
                }
                else if(!myaccount.getLoaiTK()){
                    listmyActivity = activityService.getAllMyPostNeedConfirm(myaccount.getId());
                    listmyAcitivityNeedConfirm = activityService.getAllMyActivityNeedConfirm(myaccount.getId());
                    countConfirm= activityService.countConfirm(listmyAcitivityNeedConfirm);
                    countConfirmed = activityService.countConfirmed(listmyAcitivityNeedConfirm);
                    listActivityParticipate = activityService.getListActivityParticipate(myaccount.getId());

                }
                model.addAttribute("listmyActivity",listmyActivity);
                model.addAttribute("listmyAcitivityNeedConfirm",listmyAcitivityNeedConfirm);
                model.addAttribute("countConfirm",countConfirm);
                model.addAttribute("countConfirmed",countConfirmed);
                model.addAttribute("listCancelActivity",listCancelActivity);
                model.addAttribute("listActivityParticipate",listActivityParticipate);
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
    @GetMapping("/check-activity")
    @ResponseBody
    public Map<String, Object> CheckActivity(@RequestParam("maHD") Integer maHD,Model model, HttpSession session)
    {
        Hoatdong hoatdong = activityService.getActivityByID(maHD);
        boolean isConditionMet = activityService.CheckActivity(maHD);
        sessionService.createSessionModel(model, session);
        Taikhoan myaccount = (Taikhoan) model.getAttribute("account");
        Dangky checkDangky = registerService.getDangKyByHDTK(myaccount.getId(), maHD);
        Taikhoan taikhoan =activityService.getOrganizator(maHD);
        List<Thanhvien> thanhvienList =activityService.getMemberList(maHD);
        Thanhvien thanhvien=activityService.getMemberByID(taikhoan.getId());
        Integer sotnv= activityService.countParticipantsByIDHD(maHD);
        Map<String, Object> result = new HashMap<>();
        result.put("hoatdong", hoatdong);
        result.put("isConditionMet", isConditionMet);
        result.put("taikhoan", taikhoan);
        result.put("thanhvien", thanhvien);
        result.put("thanhvienList", thanhvienList);
        result.put("checkDangky", checkDangky);
        result.put("sotnv",sotnv);
        return result;
    }

    @PostMapping("/confirm-suggestion")
    @ResponseBody
    public ResponseEntity<Void> confirmSugg(@RequestParam("idSugg") Integer idSugg) {
            suggestionService.confirmSugg(idSugg);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

    @PostMapping("/cancel-suggestion")
    @ResponseBody
    public ResponseEntity<Void>  cancelSugg(@RequestParam("idSugg") Integer idSugg) {
        suggestionService.cancelSugg(idSugg);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/de-xuat-chua-duyet")
    @ResponseBody
    public ResponseEntity<List<Dexuat>> getSuggNotConf(){
        List<Dexuat> suggestionList = suggestionService.getSuggestionNConf();
        return ResponseEntity.ok(suggestionList);
    }
}
