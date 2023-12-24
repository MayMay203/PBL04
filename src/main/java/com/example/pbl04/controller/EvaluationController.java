package com.example.pbl04.controller;

import com.example.pbl04.dao.AccountRepository;
import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.dao.SummaryRepository;
import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final ActivityService activityService;
    private final SessionService sessionService;
    private final SummaryRepository summaryRepository;
    private final RegisterService registerService;
    private final MemberService memberService;
    private final EvaluationRepository evaluationRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, ActivityService activityService,
                                SessionService sessionService,SummaryRepository summaryRepository,
                                RegisterService registerService,MemberService memberService,
                                EvaluationRepository evaluationRepository,
                                AccountRepository accountRepository){
        this.evaluationService = evaluationService;
        this.activityService = activityService;
        this.sessionService = sessionService;
        this.summaryRepository = summaryRepository;
        this.registerService = registerService;
        this.memberService = memberService;
        this.evaluationRepository = evaluationRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/trang-chu-danh-gia")
    public String getEvaluationHome(Model model, HttpSession session){
        List<Hoatdong> activityList = activityService.getActivityOccured();
        List<Danhgia> evaluationList =  evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        model.addAttribute("activityList",activityList);
        List<Integer> countEvaList = evaluationService.countEvaluation(activityList);
        model.addAttribute("countEvaList",countEvaList);
        // Kiểm tra xem người dùng đã đăng nhập chưa
        sessionService.createSessionModel(model, session);
            return "TrangChuDanhGia";
    }

    @GetMapping("/danh-gia")
    public String getAllEvaluation(Model model, @RequestParam("id") Integer id, HttpSession session){
        List<Danhgia> evaluationList = evaluationService.getAllEvaluation();
        model.addAttribute("evaluationList",evaluationList);
        List<Hoatdong> actListOfMember = activityService.getActivityByMember(id);
        model.addAttribute("actListOfMember",actListOfMember);
        List<Hoatdong> actListOfHost = activityService.getActivityByHost(id);
        model.addAttribute("actListOfHost",actListOfHost);
        sessionService.createSessionModel(model, session);
        return "DanhGia";
    }

    @GetMapping("/hoat-dong/xem-chi-tiet")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> Activity(@RequestParam("IdAct") Integer IdAct,
                                                       HttpSession session)
                                                       {
        Map<String, Object> responseData = new HashMap<>();
        Taikhoan myAcc = (Taikhoan)session.getAttribute("account");
        if(myAcc!=null){
            Integer IdAcc = myAcc.getId();
            Danhgia evaluation = evaluationService.getEvaluationByIDHDTK(IdAct,IdAcc);
            responseData.put("evaluation",evaluation);
        }
        Hoatdong activity = activityService.getActivityByID(IdAct);
        int numberEvaluation = evaluationService.countEvaByIdAct(IdAct);
        List<Danhgia> evaluationOfAct = evaluationService.getEvaluationByIdAct(IdAct);
        Dangky registerInfor = activityService.getRegisterInfo(IdAct);
        Tongket summary = summaryRepository.getSummaryByID(IdAct);
        List<Anhtongket> imagesList = new ArrayList<>();
        if(summary!=null)
        {
          imagesList  = summaryRepository.getimgSummaryList(summary.getId());
        }
        List<Dangky> registerList = registerService.getRegisterInforByIDAct(IdAct);
        List<Thanhvien> membersList = memberService.getMembersByRegis(registerList);
        List<Integer> scores = evaluationService.getMembersScoreByAct(membersList,IdAct);
        responseData.put("activity", activity);
        responseData.put("numberEvaluation", numberEvaluation);
        responseData.put("evaluationOfAct",evaluationOfAct);
        responseData.put("registerInfor",registerInfor);
        responseData.put("imagesList",imagesList);
        responseData.put("membersList",membersList);
        responseData.put("scores",scores);
        return ResponseEntity.ok(responseData);
    }

    //Danh gia hoat dong
    @PostMapping("/danh-gia/danh-gia-hoat-dong")
    @ResponseBody
    public ResponseEntity<Void> evaluateActivity(@RequestParam("maTK") Integer maTK,
                                                 @RequestParam("maHD") Integer maHD,
                                                 @RequestParam("diemTC") Integer diemTC,
                                                 @RequestParam("tieuChi1") Boolean tieuChi1,
                                                 @RequestParam("tieuChi2") Boolean tieuChi2,
                                                 @RequestParam("tieuChi3") Boolean tieuChi3,
                                                 @RequestParam("binhLuan") String binhLuan,
                                                 @RequestParam("thoiGianBL") String thoiGianBL)
    {
        Instant tgBinhLuan = Instant.parse(thoiGianBL);
       //Tim danh gia ton tai o bang chua
        Danhgia evaluation = evaluationService.getEvaluationByIDHDTK(maHD,maTK);
        if(evaluation==null){
            Danhgia newEvaluation = new Danhgia();
            Taikhoan account= accountRepository.getAccountByID(maTK);
            Hoatdong activity = activityService.getActivityByID(maHD);
            newEvaluation.setMaHD(activity);
            newEvaluation.setMaTK(account);
            newEvaluation.setDiemTC(diemTC);
            newEvaluation.setBinhLuan(binhLuan);
            newEvaluation.setTieuChi1(tieuChi1);
            newEvaluation.setTieuChi2(tieuChi2);
            newEvaluation.setTieuChi3(tieuChi3);
            newEvaluation.setThoiGianBL(tgBinhLuan);
            evaluationService.evaluateActivity(newEvaluation);
        }
        else{
            evaluationService.insertEvaluateAct(maTK,maHD,diemTC,tieuChi1,tieuChi2,tieuChi3,binhLuan,tgBinhLuan);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Danh gia thanh vien
    @PostMapping("/danh-gia/danh-gia-thanh-vien")
    @ResponseBody
    public ResponseEntity<Void> evaluateMembers(@RequestParam("maTK") Integer maTK,
                                                 @RequestParam("maHD") Integer maHD,
                                                 @RequestParam("diemTNV") Integer diemTNV)
    {
        Danhgia evaluation = evaluationService.getEvaluationByIDHDTK(maHD,maTK);
        if(evaluation==null){
            Danhgia newEvaluation = new Danhgia();
            Taikhoan account= accountRepository.getAccountByID(maTK);
            Hoatdong activity = activityService.getActivityByID(maHD);
            newEvaluation.setMaHD(activity);
            newEvaluation.setMaTK(account);
            newEvaluation.setDiemTNV(diemTNV);
            evaluationService.evaluateMember(newEvaluation);
        }
        else{
            evaluationService.insertEvaluateMemb(maTK, maHD, diemTNV);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
