package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.entity.Taikhoan;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import com.example.pbl04.service.SessionService;
import com.example.pbl04.service.SuggestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    private final ActivityService activityService;
    private final EvaluationService evaluationService;
    private final SuggestionService suggestionService;
    private final SessionService sessionService;
    @Autowired

    public SearchController(ActivityService activityService, EvaluationService evaluationService, SuggestionService suggestionService,SessionService sessionService) {
        this.activityService = activityService;
        this.evaluationService = evaluationService;
        this.suggestionService = suggestionService;
        this.sessionService = sessionService;
    }

    @GetMapping("search/evaluation-home")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> searchActivity(@RequestParam(name="nameAct",required = false) String nameAct){
        Map<String,Object> result = new HashMap<>();
        List<Hoatdong> activityList = new ArrayList<>();
        List<Integer> countEvaList = new ArrayList<>();
//        System.out.println(nameAct);
        if(nameAct == null){
            System.out.println("vào đây rồi nè!");
           activityList = activityService.getActivityOccured();
            countEvaList = evaluationService.countEvaluation(activityList);
        }
        else{
//            String name = nameAct.replaceAll("-"," ");
            activityList = activityService.getActOccuredByName(nameAct);
            countEvaList = evaluationService.countEvaluation(activityList);
        }
        result.put("activityList",activityList);
        result.put("countEvaList",countEvaList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/evaluation")
    @ResponseBody
    public ResponseEntity<List<Hoatdong>> searchJoinedAct(@RequestParam(name="nameAct",required = false) String nameAct,@RequestParam(name="IdAcc")Integer IdAcc){
        List<Hoatdong> actListOfMember = new ArrayList<>();
        if(nameAct.isEmpty()){
            actListOfMember = activityService.getActivityByMember(IdAcc);
        }
        else{
            actListOfMember = activityService.getActOfMemberByName(IdAcc,nameAct);
        }
        return ResponseEntity.ok(actListOfMember);
    }


    @GetMapping("search/suggestion")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> searchSuggestion(@RequestParam(name="nameTitle",required = false) String nameTitle,
                                                               HttpSession session
                                                             ){
       Map<String,Object> result = new HashMap<>();
        List<Dexuat> suggestionList = new ArrayList<>();
        List<String> locationList  = new ArrayList<>();
        List<Integer> countAct = new ArrayList<>();
        Taikhoan account = (Taikhoan) session.getAttribute("account");
        System.out.println("Tài khoản nè he" + account);
        if(nameTitle == null){
            suggestionList = suggestionService.getAllSuggest();
            for(Dexuat sugg:suggestionList){
                locationList.add(sugg.getViTri());
            }
            //Dem hoat dong theo vi tri
            countAct = activityService.countActByLocation(locationList);
        }
        else{
            suggestionList = suggestionService.getSuggestionByTitle(nameTitle);
            for(Dexuat sugg:suggestionList){
                locationList.add(sugg.getViTri());
            }
            //Dem hoat dong theo vi tri
            countAct = activityService.countActByLocation(locationList);
        }
        result.put("suggestionList",suggestionList);
        result.put("account",account);
        result.put("countAct",countAct);
        return ResponseEntity.ok(result);
    }
}
