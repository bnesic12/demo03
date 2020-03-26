package com.bnesic12.demo03.services;

import com.bnesic12.demo03.dto.ScrabbleEntity;
import org.springframework.stereotype.Component;

@Component
public class ScrabbleServiceImpl implements IScrabbleService {
    public ScrabbleEntity getScrabble() {
        return new ScrabbleEntity();
    }
}
