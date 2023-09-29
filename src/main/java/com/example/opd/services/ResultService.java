package com.example.opd.services;

import com.example.opd.models.Result;
import com.example.opd.repositories.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;

    public void save(Result result) {
        resultRepository.save(result);
    }

    public List<Result> getAllResult() {
        List<Result> results = resultRepository.findAll();
        return results;
    }

    public List<Result> getAllResultByUser(Principal principal) {
        if (principal == null) return new ArrayList<>();
        return resultRepository.findAllByUserEmail(principal.getName());
    }

    public List<Result> findAllByUserEmailAndTest_id(Principal principal, Long test_id) {
        if (principal == null) return new ArrayList<>();
        return resultRepository.findAllByUserEmailAndTestId(principal.getName(), test_id);
    }
    @Transactional
    public void removeByUserEmailAndAndTestId(Principal principal, Long test_id) {
        resultRepository.removeAllByUserEmailAndTestId(principal.getName(), test_id);
    }


}

