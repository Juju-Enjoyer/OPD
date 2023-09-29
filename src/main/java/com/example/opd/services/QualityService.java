package com.example.opd.services;

import com.example.opd.models.Block;
import com.example.opd.models.Quality;
import com.example.opd.repositories.BlockRepository;
import com.example.opd.repositories.QualityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class QualityService {
    private final QualityRepository qualityRepository;
    public List<Quality> getQuality() {
        List<Quality> qualities = qualityRepository.findAll();
        return qualities;
    }

}
