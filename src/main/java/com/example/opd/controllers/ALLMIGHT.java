package com.example.opd.controllers;

import com.example.opd.models.Result;
import com.example.opd.models.User;
import com.example.opd.services.ResultService;
import com.example.opd.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ALLMIGHT {
    private final UserService userService;
    private final ResultService resultService;



    public ALLMIGHT(UserService userService, ResultService resultService) {
        this.userService = userService;
        this.resultService = resultService;
    }

    @GetMapping("/AllMight")
    public String allMight(Model model){
        return "/all/AllMight";
    }



    @GetMapping("/AllMight/SoundTest")
    public String stF (){
        return "/all/SoundTest(all)";
    }

    @PostMapping("/AllMight/ST")
    public String saveResSoundTest(@RequestParam(value = "result") String results,
                                   @RequestParam(value = "testId") Long id, Principal principal,
                                   Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Long> list = mapper.readValue(results, new TypeReference<List<Long>>() {
        });


        long testId = id;
        float avgRes = 0;
        for (Long res : list) {
            avgRes += res;
        }
        avgRes = (avgRes + 100) / 10;

        int successRate = 0;
        for (Long res : list) {
            if (res <= avgRes) {
                successRate += 1;
            }
        }

        User user = userService.getUserByPrincipal(principal);
        Result result = new Result();
        result.setTestId(101L);
        result.setTest_pass_rate(successRate * 10);
        result.setUser(user);
        if(!resultService.findAllByUserEmailAndTest_id(principal,101L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,101L);
        }
        resultService.save(result);
        return "/all/TestNumber(all)";
    }


    @GetMapping("/AllMight/NumberTest")
    public String ntF(){
        return "all/TestNumber(all)";
    }



    @PostMapping("/AllMight/NumberTest")
    public String saveResultNumberTest(@RequestParam(value = "result") List<Boolean> result,
                                       @RequestParam(value = "resTime") List<Long> resTime,
                                       Principal principal,
                                       Model model
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Boolean> resultList = result;
        List<Long> resTimeList = resTime;
        long idealTime = 2000;
        int answer = 0;
        for(int i=0; i<10;i++){
            if (resTimeList.get(i)<=idealTime){
                if(resultList.get(i)){
                    answer++;
                }
            }
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(102L);
        finalResult.setTest_pass_rate(answer * 10);
        finalResult.setUser(user);
        if(!resultService.findAllByUserEmailAndTest_id(principal,102L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,102L);
        }
        resultService.save(finalResult);
        return "/all/ColorTest(all)";
    }





    @PostMapping("/AllMight/ColorTest")
    public String saveResColorTest(@RequestParam(value = "result") String results,
                                   @RequestParam(value = "testId") Long id,
                                   Principal principal, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Long> list = mapper.readValue(results, new TypeReference<List<Long>>() {
        });

        User user = userService.getUserByPrincipal(principal);
        float avgRes = 0;
        for (Long res : list) {
            avgRes += res;
        }
        avgRes = (avgRes + 100) / 10;

        int successRate = 0;
        for (Long res : list) {
            if (res <= avgRes) {
                successRate += 1;
            }
        }


        Result result = new Result();
        result.setTestId(103L);
        result.setTest_pass_rate(successRate * 10);
        result.setUser(user);
        if(!resultService.findAllByUserEmailAndTest_id(principal,103L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,103L);
        }
        resultService.save(result);
        return "/all/ColorTestHard(all)";
    }


    @PostMapping("/AllMight/ColorTestHard")
    public String saveResultColorTestHard(@RequestParam(value = "result") List<Boolean> result,
                                          @RequestParam(value = "resultTime") List<Long> resTime,
                                          @RequestParam(value = "testId")Long testId,
                                          Principal principal,
                                          Model model
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Boolean> resultList = result;
        List<Long> resTimeList = resTime;
        long idealTime = 2000;
        int answer = 0;
        for(int i=0; i<10;i++){
            if (resTimeList.get(i)<=idealTime){
                if(resultList.get(i)){
                    answer++;
                }
            }
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(answer*10);
        finalResult.setTestId(104L);
        if(!resultService.findAllByUserEmailAndTest_id(principal,104L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,104L);
        }
        resultService.save(finalResult);
        return "/all/moveTest(all)";
    }


    @PostMapping("/AllMight/moveTest")
    public String MoveTestSaveResult(@RequestParam(value = "hit") int hits,
                                     @RequestParam(value = "click") int clicks,
                                     @RequestParam(value = "time") List<Long> times,
                                     Model model,
                                     Principal principal) {

        double accuracy = 0.0;
        if (clicks == 0) {
            accuracy = 0.0;
        } else {
            accuracy = (double) hits / clicks * 100;
        }
        long sub = 0L;
        double averageTime = 0.0;
        if (times.isEmpty()) {
            sub = 0L;
        } else {
            for (long time : times) {
                sub += time;
            }
            averageTime = (double) sub / times.size();
        }
        double accuracyScore = accuracy * 0.5;
        double reactionTimeScore = (1 - averageTime / 6000) * 50;
        int score = (int) ((int) accuracyScore + reactionTimeScore);


        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(105L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        if(!resultService.findAllByUserEmailAndTest_id(principal,105L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,105L);
        }
        resultService.save(finalResult);
        return "all/moveTestHard(all)";
    }


    @PostMapping("/AllMight/moveTestHard")
    public String moveTestHardResult(@RequestParam(value = "hit") List<Integer> hits,
                                     @RequestParam(value = "click") List<Integer> clicks,
                                     @RequestParam(value = "time1") List<Long> times1,
                                     @RequestParam(value = "time2") List<Long> times2,
                                     @RequestParam(value = "time3") List<Long> times3,
                                     Model model,
                                     Principal principal) {
        int hit1 = hits.get(0);
        int hit2 = hits.get(1);
        int hit3 = hits.get(2);

        int click1 = clicks.get(0);
        int click2 = clicks.get(1);
        int click3 = clicks.get(2);

        double accuracy1 = getAccuracy(hit1, click1);
        double accuracy2 = getAccuracy(hit2, click2);
        double accuracy3 = getAccuracy(hit3, click3);

        double accuracyScore1 = accuracy1 * 0.1;
        double reactionTimeScore1 = (1 - getAverageTime(times1) / 6000) * 15;

        double accuracyScore2 = accuracy2 * 0.15;
        double reactionTimeScore2 = (1 - getAverageTime(times2) / 6000) * 15;

        double accuracyScore3 = accuracy3 * 0.25;
        double reactionTimeScore3 = (1 - getAverageTime(times3) / 6000) * 20;

        int score = (int) (accuracyScore1 + reactionTimeScore1 + accuracyScore2 + reactionTimeScore2 + accuracyScore3 + reactionTimeScore3);

        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(106L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        if(!resultService.findAllByUserEmailAndTest_id(principal,106L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,106L);
        }
        resultService.save(finalResult);
        return "all/RavenTest(all)";


    }

    public double getAverageTime(List<Long> times) {
        long sub = 0L;
        if (times.isEmpty()) {
            return 0.0;
        } else {
            for (long time : times) {
                sub += time;
            }
            return (double) sub / times.size();
        }
    }

    public double getAccuracy(int hit, int click) {

        if (click == 0) {
            return 0.0;
        } else {
            return (double) hit / click * 100;
        }
    }

    @PostMapping("/AllMight/RavenTest")
    public String RavenTestResult(@RequestParam(value = "aRes") List<Integer> a,
                                  @RequestParam(value = "bRes") List<Integer> b,
                                  @RequestParam(value = "cRes") List<Integer> c,
                                  @RequestParam(value = "dRes") List<Integer> d,
                                  @RequestParam(value = "eRes") List<Integer> e,
                                  Model model,
                                  Principal principal) {
        int aRes = res(a);
        int bRes = res(b);
        int cRes = res(c);
        int dRes = res(d);
        int eRes = res(e);


        int score = (int) ((aRes + bRes * 1.5 + cRes * 2 + dRes * 2.5 + eRes * 3) / (120) * 100);
        System.out.println(score);


        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(107L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        if(!resultService.findAllByUserEmailAndTest_id(principal,107L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,107L);
        }
        resultService.save(finalResult);
        return "all/RedBlack(all)";
    }

    public int res(List<Integer> list) {
        int score = 0;
        for (int i : list) {
            score += i;
        }
        return score;
    }

    @PostMapping("/AllMight/RedBlackTest")
    public String redBlackSaveResult(@RequestParam(value = "miss") int miss,
                                     @RequestParam(value = "time") List<Long> time, Model model,
                                     Principal principal) {
        int score = (int) ((1 - miss / 49) * 100);
        if (score < 0) {
            score = 0;
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(108L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        if(!resultService.findAllByUserEmailAndTest_id(principal,108L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,108L);
        }
        resultService.save(finalResult);
        return "all/MemoryTest(all)";
    }

    @PostMapping("/AllMight/MemoryTest")
    public String memoryTestSaveResult(@RequestParam(value = "ideal") List<Integer> ideal,
                                       @RequestParam(value = "misplace") List<Integer> misplace,
                                       Model model,
                                       Principal principal) {
        int lvl1 = (int) Math.round( (ideal.get(0) + misplace.get(0) * 0.5));
        int lvl2 = (int) Math.round( (ideal.get(0) * 1.5 + misplace.get(0) * 0.75));
        int lvl3 = (int) Math.round( (ideal.get(0) * 1.67 + misplace.get(0) * 0.83));
        int lvl4 = (int) Math.round( (ideal.get(0) * 1.75 + misplace.get(0) * 0.88));
        int lvl5 = (int) Math.round( (ideal.get(0) * 1.80 + misplace.get(0) * 0.9));
        int lvl6 = (int) Math.round( (ideal.get(0) * 1.67 + misplace.get(0) * 0.83));
        int lvl7 = (int) Math.round( (ideal.get(0) * 1.71 + misplace.get(0) * 0.86));
        int lvl8 = (int) Math.round( (ideal.get(0) * 1.88 + misplace.get(0) * 0.94));
        int lvl9 = (int) Math.round( (ideal.get(0) * 2 + misplace.get(0)));
        int lvl10 = (int) Math.round( (ideal.get(0) * 2 + misplace.get(0)));
        int score = lvl1 + lvl2 + lvl3 + lvl4 + lvl5 + lvl6 + lvl7 + lvl8 + lvl9 + lvl10;

        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(109L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        if(!resultService.findAllByUserEmailAndTest_id(principal,109L).isEmpty()){
            resultService.removeByUserEmailAndAndTestId(principal,109L);
        }
        resultService.save(finalResult);


   List<Integer> result = new ArrayList<>();
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,101L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,102L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,103L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,104L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,105L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,106L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,107L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,108L).get(0).getTest_pass_rate());
        result.add((int) resultService.findAllByUserEmailAndTest_id(principal,109L).get(0).getTest_pass_rate());


        double feb = feb(result);
        double soft = soft(result);
        double sis = sis(result);
        double maxResult = Math.max(Math.max(feb, soft), sis);
        double[] prof = new double[3];


        prof[0] = feb;
        prof[1] = soft;
        prof[2] = sis;
        model.addAttribute("prof", prof);
        resultService.removeByUserEmailAndAndTestId(principal,101L);
        resultService.removeByUserEmailAndAndTestId(principal,102L);
        resultService.removeByUserEmailAndAndTestId(principal,103L);
        resultService.removeByUserEmailAndAndTestId(principal,104L);
        resultService.removeByUserEmailAndAndTestId(principal,105L);
        resultService.removeByUserEmailAndAndTestId(principal,106L);
        resultService.removeByUserEmailAndAndTestId(principal,107L);
        resultService.removeByUserEmailAndAndTestId(principal,108L);
        resultService.removeByUserEmailAndAndTestId(principal,109L);

        return "all/final";
    }


public double feb(List<Integer> result){
        double sum = 0;
        sum += ((double) result.get(0) /100)*25;
        sum += ((double) result.get(2) /100)*25;
        sum += ((double) result.get(3) /100)*25;
        sum += ((double) result.get(7) /100)*25;
        return sum;
}
    public double soft(List<Integer> result){
        double sum = 0;

        sum += ((double) result.get(1) /100)*25;
        sum += ((double) result.get(6) /100)*25;
        sum += ((double) result.get(7) /100)*25;
        sum += ((double) result.get(8) /100)*25;
        return sum;
    }
    public double sis(List<Integer> result){
        double sum = 0;
        sum += ((double) result.get(4) /100)*25;
        sum += ((double) result.get(5) /100)*25;
        sum += ((double) result.get(0) /100)*25;
        sum += ((double) result.get(6) /100)*25;
        return sum;
    }






}
