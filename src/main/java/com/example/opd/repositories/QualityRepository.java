package com.example.opd.repositories;

import com.example.opd.models.Block;
import com.example.opd.models.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QualityRepository extends JpaRepository<Quality, Long> {


    /*@Query("select q.name  " +
            "FROM Block b " +
            "join Grade g ON g.blockId = b.id " +
            "JOIN Quality q ON g.id_quality = q.id "+
            "GROUP BY q.id, q.name " +
            "ORDER BY AVG(g.quality_grade) DESC, q.id DESC")
    List<String> findQualitiesByBlockIdOrderByAvgRatingDesc(@Param("b.id") Long id);*/


}
