package com.example.pbl04.controller;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Dexuat;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
import com.example.pbl04.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("search/evalatuation-home/{nameAct}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> searchActivity(@PathVariable(name="nameAct",required = false) String nameAct){
        Map<String,Object> result = new HashMap<>();
        List<Hoatdong> activityList = new ArrayList<>();
        List<Integer> countEvaList = new ArrayList<>();
        if(nameAct.isEmpty()){
           activityList = activityService.getActivityOccured();
            countEvaList = evaluationService.countEvaluation(activityList);
        }
        else{
            String name = nameAct.replaceAll("-"," ");
            activityList = activityService.getActOccuredByName(name);
            countEvaList = evaluationService.countEvaluation(activityList);
        }
        result.put("activityList",activityList);
        result.put("countEvaList",countEvaList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("search/evaluation/{nameAct}")
    @ResponseBody
    public ResponseEntity<List<Hoatdong>> searchJoinedAct(@PathVariable(name="nameAct",required = false) String nameAct){
//        Map<String,Object> rs = new HashMap<>();
        List<Hoatdong> actListOfMember = new ArrayList<>();
//        List<Integer> countEvaList = new ArrayList<>();
        if(nameAct.isEmpty()){
            actListOfMember = activityService.getActivityByMember(7);
//            countEvaList = evaluationService.countEvaluation(actListOfMember);
        }
        else{
            String name = nameAct.replaceAll("-"," ");
            actListOfMember = activityService.getActOfMemberByName(7,name);
//            countEvaList = evaluationService.countEvaluation(actListOfMember);
        }
//        rs.put("actListOfMember",actListOfMember);
//        rs.put("countEvaList",countEvaList);
        return ResponseEntity.ok(actListOfMember);
    }


    @GetMapping("search/suggestion/{nameTitle}")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> searchSuggestion(@PathVariable(name="nameTitle",required = false) String nameTitle){
       Map<String,Object> result = new HashMap<>();
        List<Dexuat> suggestionList = new ArrayList<>();
        List<Integer> countSugg = new ArrayList<>();
        System.out.println(nameTitle);
        if(nameTitle.isEmpty()){
            suggestionList = suggestionService.getSuggestionByTitle(nameTitle);
            countSugg = suggestionService.CountSuggestion(suggestionList);
        }
        else{
            String name = nameTitle.replaceAll("-"," ");
            suggestionList = suggestionService.getSuggestionByTitle(name);
            countSugg = suggestionService.CountSuggestion(suggestionList);
        }
        result.put("suggestionList",suggestionList);
        result.put("countSugg",countSugg);
        return ResponseEntity.ok(result);
    }
}
