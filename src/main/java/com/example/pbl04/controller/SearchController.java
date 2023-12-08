package com.example.pbl04.controller;

import com.example.pbl04.dao.ActivityRepository;
import com.example.pbl04.dao.EvaluationRepository;
import com.example.pbl04.entity.Hoatdong;
import com.example.pbl04.service.ActivityService;
import com.example.pbl04.service.EvaluationService;
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

    public SearchController(ActivityService activityService,EvaluationService evaluationService) {
        this.activityService = activityService;
        this.evaluationService = evaluationService;
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
}
