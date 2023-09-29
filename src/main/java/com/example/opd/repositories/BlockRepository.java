package com.example.opd.repositories;

import com.example.opd.models.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findBlockByTitle(String title);

}
