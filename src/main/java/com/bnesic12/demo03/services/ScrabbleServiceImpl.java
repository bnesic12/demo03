package com.bnesic12.demo03.services;

import com.bnesic12.demo03.dto.ScrabbleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrabbleServiceImpl implements IScrabbleService {

    @Autowired
    ScrabbleEntity scrabbleEntity;

    public ScrabbleEntity getScrabble() {
        //return new ScrabbleEntity();
        return scrabbleEntity;
    }
}
