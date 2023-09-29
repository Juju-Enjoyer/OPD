package com.example.opd.repositories;

import com.example.opd.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findAllByUserEmail(String email);
    List<Result> findAllByUserEmailAndTestId(String email, Long test_id);
    void removeAllByUserEmailAndTestId(String email,Long test_id);
    List<Result> findByUserEmailAndTestId(String email,Long test_id);
}
