package com.example.opd.repositories;

import com.example.opd.models.Grade;
import com.example.opd.models.Quality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByBlockId(Long idBlock);
    @Query("SELECT AVG(g.quality_grade) FROM Grade g WHERE g.blockId = :blockId AND g.id_quality = :qualityId")
    Double getAverageQualityGrade(@Param("blockId") Long blockId, @Param("qualityId") Long qualityId);
    @Query("select count(g.quality_grade) from Grade  g where g.blockId = :blockId AND g.id_quality = :qualityId")
    int getCountQualityGrade(@Param("blockId") Long blockId, @Param("qualityId") Long qualityId);


}
