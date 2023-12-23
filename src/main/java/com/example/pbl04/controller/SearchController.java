package com.example.pbl04.controller;

import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import com.example.pbl04.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @Autowired

    public SearchController(ActivityService activityService, EvaluationService evaluationService, SuggestionService suggestionService) {
        this.activityService = activityService;
        this.evaluationService = evaluationService;
        this.suggestionService = suggestionService;
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
    public ResponseEntity<Map<String,Object>> searchSuggestion(@RequestParam(name="nameTitle",required = false) String nameTitle){
       Map<String,Object> result = new HashMap<>();
        List<Dexuat> suggestionList = new ArrayList<>();
//        List<Integer> countSugg = new ArrayList<>();
//        if(nameTitle == null){
//            suggestionList = suggestionService.getAllSuggest();
//            countSugg = suggestionService.CountSuggestion(suggestionList);
//        }
//        else{
//            suggestionList = suggestionService.getSuggestionByTitle(nameTitle);
//            countSugg = suggestionService.CountSuggestion(suggestionList);
//        }
        result.put("suggestionList",suggestionList);
     //   result.put("countSugg",countSugg);
        return ResponseEntity.ok(result);
    }
}
