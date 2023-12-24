package com.example.pbl04.controller;

import com.example.pbl04.entity.*;
import com.example.pbl04.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {
    private final MemberService memberService;
    private final EvaluationService evaluationService;
    private final ActivityService activityService;
    private final AboutUsService aboutUsService;
    private final SummaryService summaryService;
    private final SessionService sessionService;
    @Autowired
    public HomePageController(MemberService memberService, EvaluationService evaluationService, ActivityService activityService, AboutUsService aboutUsService, SummaryService summaryService, SessionService sessionService){
        this.memberService = memberService;
        this.evaluationService = evaluationService;
        this.activityService = activityService;
        this.aboutUsService = aboutUsService;
        this.summaryService = summaryService;
        this.sessionService = sessionService;
    }
//    @GetMapping("/trang-chu")
//    public String showMember(Model model){
//        List<Thanhvien> memberList = memberService.getAllMember();
//        model.addAttribute("memberList", memberList);
//        return "TrangChu";
//    }
    @GetMapping("/trang-chu")
    public String show(Model model, HttpSession session)
    {
        List<Thanhvien> memberList = memberService.getAllMember();
        model.addAttribute("memberList", memberList);

        List<Integer> totalRates = evaluationService.getTotalRateOfMember();
        model.addAttribute(("totalRates"),totalRates);


        List<Hoatdong> activitiesList = activityService.getAllActivity();
        model.addAttribute("activitiesList",activitiesList);


        List<Vetrangweb> aboutUs = aboutUsService.getAllAboutUs();
        model.addAttribute("aboutUs",aboutUs);

        List<Tongket> summaryList = summaryService.getSummaryList();
        model.addAttribute("summaryList",summaryList);

        List<Hoatdong> listActiSummarried = new ArrayList<>();
        for(Tongket tk : summaryList){
            listActiSummarried.add(tk.getMaHD());
        }

        List<List<Danhgia>> evaluateListByIDList = evaluationService.getEvaluationByIdActList(listActiSummarried);
        model.addAttribute("evaluateListByIDList",evaluateListByIDList);
        for (List<Danhgia> x : evaluateListByIDList) {

            for (Danhgia xx : x) {
                System.out.println(xx.getMaHD().getTenHD());
                System.out.println(xx);
                // In ra các thuộc tính của đối tượng Danhgia
                System.out.println("Id: " + xx.getId());
                System.out.println("BinhLuan: " + xx.getBinhLuan());
                // Thêm các thuộc tính khác nếu cần
                System.out.println("-------------------");
            }
        }


//        List<Danhgia> evaluationOfAct = evaluationService.getEvaluationByIdAct(id);

        sessionService.createSessionModel(model, session);

        return "TrangChu";
    }
}


