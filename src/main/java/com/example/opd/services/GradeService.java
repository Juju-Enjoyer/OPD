package com.example.opd.services;

import com.example.opd.models.Block;
import com.example.opd.models.Grade;
import com.example.opd.models.Quality;
import com.example.opd.models.User;
import com.example.opd.repositories.GradeRepository;
import com.example.opd.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private  final UserService userService;
    private final QualityService qualityService;

    public List<Grade> getGrade() {
        List<Grade> grades = gradeRepository.findAll();
        return grades;
    }

    public void saveGrade(Principal principal, Grade grade) {
        grade.setUser(userService.getUserByPrincipal(principal));
        log.info("Saving new {}", grade);
        gradeRepository.save(grade);
    }

    public void saveAllGrade(List<Grade> grades) {
        log.info("Saving new {}", grades);
        gradeRepository.saveAll(grades);
    }

    public List<Grade> getEntitiesByBlockId(Long block_id) {
        List<Grade> grades = gradeRepository.findByBlockId(block_id);
        return grades;
    }

    public double getAvgQuality(Long block, Long quality) {
        Double avg = gradeRepository.getAverageQualityGrade(block, quality);
        if (avg == null) {
            avg = 0.0;
            return avg;
        } else {
            return avg;
        }
    }

    public int getCountQuality(Long block, Long quality){
        Integer count = gradeRepository.getCountQualityGrade(block, quality);
        if (count == null) {
            count = 0;
            return count;
        } else {
            return count;
        }
    }


    public List<Grade> sortEntitiesByBlockIdAndGradeQuality(Long block_id){
        List<Grade> grades = gradeRepository.findByBlockId(block_id);
        grades.sort(Comparator.comparing(Grade::getQuality_grade).reversed());
        return grades;
    }

}
