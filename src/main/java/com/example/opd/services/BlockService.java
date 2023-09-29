package com.example.opd.services;

import com.example.opd.models.Block;
import com.example.opd.repositories.BlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;



    public List<Block> getBlocks() {
        List<Block> blocks = blockRepository.findAll();
        return blocks;
    }
    public void saveBlock(Block block){
        log.info("Saving new {}",block);
        blockRepository.save(block);
    }
    public void deleteBlock(Long id){
        blockRepository.deleteById(id);
    }

    public Block getBlocksById(Long id){
       return blockRepository.findById(id).orElse(null);
    }
}


