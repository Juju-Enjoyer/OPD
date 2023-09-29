package com.example.opd.controllers;

import com.example.opd.models.Result;
import com.example.opd.models.User;
import com.example.opd.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

@Controller
public class TestController {
    private final BlockService blockService;
    private final QualityService qualityService;
    private final GradeService gradeService;
    private final UserService userService;
    private final ResultService resultService;

    public TestController(UserService userService, BlockService blockService, QualityService qualityService, GradeService gradeService, ResultService resultService) {
        this.blockService = blockService;
        this.qualityService = qualityService;
        this.gradeService = gradeService;
        this.userService = userService;
        this.resultService = resultService;
    }


    @GetMapping("/static/turkish-march-mozart-rondo-alla-turca.mp3")
    public ResponseEntity<byte[]> getSound() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/turkish-march-mozart-rondo-alla-turca.mp3");
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

    @GetMapping("/static/newST.js")
    public ResponseEntity<byte[]> getSoundTestJs() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/newST.js");
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/javascript"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

    @GetMapping("/static/ColorTest.js")
    public ResponseEntity<byte[]> getColorTestJs() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/ColorTest.js");
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/javascript"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

    @GetMapping("/static/ColorTestHard.js")
    public ResponseEntity<byte[]> getColorTestHardJs() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/ColorTestHard.js");
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/javascript"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

    @PostMapping("/TestLobby/SoundTest/saveResult")
    public String saveResSoundTest(@RequestParam(value = "result") String results,
                                   @RequestParam(value = "testId") Long id,
                                   Principal principal, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Long> list = mapper.readValue(results, new TypeReference<List<Long>>() {
        });

        User user = userService.getUserByPrincipal(principal);
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
        Result result = new Result();
        result.setTestId(testId);
        result.setTest_pass_rate(successRate * 10);
        result.setUser(user);
        resultService.save(result);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 1L));
        return "redirect:/TestLobby/SoundTest";
    }

    @GetMapping("/TestLobby/SoundTest")
    public String SoundTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 1L));
        return "SoundTest";
    }

    @GetMapping("/TestLobby")
    public String TestLobbyFront(Principal principal) {
        return "TestLobby";
    }

    @GetMapping("/static/testNumber.js")
    public ResponseEntity<byte[]> getNumberTestJs() throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/testNumber.js");
        byte[] media = IOUtils.toByteArray(in);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/javascript"));
        headers.setContentLength(media.length);
        return new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
    }

    @PostMapping("/TestLobby/NumberTest/saveResult")
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
        for (int i = 0; i < 10; i++) {
            if (resTimeList.get(i) <= idealTime) {
                if (resultList.get(i)) {
                    answer++;
                }
            }
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(answer * 10);
        finalResult.setTestId(2L);
        resultService.save(finalResult);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 2L));
        return "redirect:/TestLobby/NumberTest";
    }

    @GetMapping("/TestLobby/NumberTest")
    public String NumberTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 2L));
        return "TestNumber";
    }

    @GetMapping("/TestLobby/ColorTest")
    public String ColorTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 3L));
        return "ColorTest";
    }

    @PostMapping("/TestLobby/ColorTest/saveResult")
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
        result.setTestId(id);
        result.setTest_pass_rate(successRate * 10);
        result.setUser(user);
        resultService.save(result);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, id));
        return "redirect:/TestLobby/ColorTest";
    }

    @PostMapping("/TestLobby/ColorTestHard/saveResult")
    public String saveResultColorTestHard(@RequestParam(value = "result") List<Boolean> result,
                                          @RequestParam(value = "resultTime") List<Long> resTime,
                                          @RequestParam(value = "testId") Long testId,
                                          Principal principal,
                                          Model model
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Boolean> resultList = result;
        List<Long> resTimeList = resTime;
        long idealTime = 2000;
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (resTimeList.get(i) <= idealTime) {
                if (resultList.get(i)) {
                    answer++;
                }
            }
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(answer * 10);
        finalResult.setTestId(testId);
        resultService.save(finalResult);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, testId));
        return "redirect:/TestLobby/ColorTestHard";
    }

    @GetMapping("/TestLobby/ColorTestHard")
    public String ColorTestHardFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 4L));
        return "ColorTestHard";
    }

    @GetMapping("/TestLobby/moveTest")
    public String MoveTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 5L));
        return "moveTest";
    }

    @PostMapping("/TestLobby/moveTest/saveResult")
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
        finalResult.setTestId(5L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        resultService.save(finalResult);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 5L));
        return "redirect:/TestLobby/moveTest";
    }

    @GetMapping("/TestLobby/moveTestHard")
    public String MoveTestHardFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 5L));
        return "moveTestHard";
    }

    @PostMapping("/TestLobby/moveTestHard/saveResult")
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
        finalResult.setTestId(6L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        resultService.save(finalResult);
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 6L));
        return "redirect:/TestLobby/moveTestHard";


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


    @GetMapping("/TestLobby/RavenTest")
    public String RavenTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 7L));
        return "RavenTest";
    }

    @PostMapping("/TestLobby/RavenTest/saveResult")
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
        finalResult.setTestId(7L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        resultService.save(finalResult);
        return "redirect:/TestLobby/RavenTest";
    }

    public int res(List<Integer> list) {
        int score = 0;
        for (int i : list) {
            score += i;
        }
        return score;
    }

    @GetMapping("/TestLobby/RedBlackTest")
    public String redBlackFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 8L));
        return "RedBlack";
    }

    @PostMapping("/TestLobby/RedBlackTest/saveResult")
    public String redBlackSaveResult(@RequestParam(value = "miss") int miss,
                                     @RequestParam(value = "time") List<Long> time, Model model,
                                     Principal principal) {
        int score = (int) ((1 - miss / 49) * 100);
        if (score < 0) {
            score = 0;
        }
        User user = userService.getUserByPrincipal(principal);
        Result finalResult = new Result();
        finalResult.setTestId(8L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        resultService.save(finalResult);
        return "redirect:/TestLobby/RedBlackTest";
    }

    @GetMapping("/TestLobby/MemoryTest")
    public String memoryTestFront(Model model, Principal principal) {
        model.addAttribute("results", resultService.findAllByUserEmailAndTest_id(principal, 9L));
        return "MemoryTest";
    }

    @PostMapping("/TestLobby/MemoryTest/saveResult")
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
        finalResult.setTestId(9L);
        finalResult.setUser(user);
        finalResult.setTest_pass_rate(score);
        resultService.save(finalResult);
        return "redirect:MemoryTest";
    }
}