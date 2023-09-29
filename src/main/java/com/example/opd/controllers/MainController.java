package com.example.opd.controllers;

import com.example.opd.models.Grade;
import com.example.opd.models.Result;
import com.example.opd.models.User;
import com.example.opd.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;


@Controller
public class MainController {

    private final BlockService blockService;
    private final QualityService qualityService;
    private final GradeService gradeService;
    private final UserService userService;
    private final ResultService resultService;

    public MainController(UserService userService, BlockService blockService, QualityService qualityService, GradeService gradeService, ResultService resultService){
        this.blockService=blockService;
        this.qualityService=qualityService;
        this.gradeService=gradeService;
        this.userService= userService;
        this.resultService=resultService;
    }

    @GetMapping("/")
    public String main(Model model, Principal principal) {
        model.addAttribute("blocks",blockService.getBlocks());
        model.addAttribute("user", userService.getUserByPrincipal(principal));

        return "main";
    }
@GetMapping("block/info/{id}")
    public String blockInfo(@PathVariable Long  id,Model model, Principal principal){
model.addAttribute("block",blockService.getBlocksById(id));
model.addAttribute("pvk",qualityService.getQuality());
model.addAttribute("sortGrade",gradeService.getEntitiesByBlockId(id));
model.addAttribute("gradeService",gradeService);
model.addAttribute("user", userService.getUserByPrincipal(principal));
/*model.addAttribute("aboba", qualityService.getSort(id));*/




return "block-info";
    }


//    @GetMapping("/user/create")
//    public String createUser(User user){

//        return "redirect";
//    }

    @PostMapping("/block/info/{id}/quality")
    public String GiveQuality(@PathVariable Long id,
                              @RequestParam(value = "id_quality", required = false) List<Long> qualityIds,
                              @RequestParam(value = "quality_grade", required = false) List<Integer> qualityGrades, Model model, Principal principal) {
        System.out.println("aло гараж ");


    // Создаем список оценок на основе полученных данных
    List<Grade> grades = new ArrayList<>();
    model.addAttribute("grades",grades);
    for (int i = 0; i < qualityIds.size(); i++) {
        Grade grade = new Grade();
        grade.setBlockId(id);
        grade.setId_quality(qualityIds.get(i));
        grade.setQuality_grade(qualityGrades.get(i));
        grades.add(grade);
        gradeService.saveGrade(principal,grade);
    }

return "redirect:/block/info/{id}";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUSer(@PathVariable Long id){

        return "redirect";
    }

   /* @GetMapping("/block/info/{id}")
    public String showGradeForBlock(@PathVariable Long id,Model model){
        model.addAttribute("sortGrade",gradeService.getEntitiesByBlockId(id));
        return "redirect:/block/info/{id}";
    }*/





}

