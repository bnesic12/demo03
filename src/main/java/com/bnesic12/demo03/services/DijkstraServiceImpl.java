package com.bnesic12.demo03.services;

import com.bnesic12.demo03.dto.DNetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DijkstraServiceImpl implements IDijkstraService {
    @Autowired
    DNetEntity dne;

    public DNetEntity getDNet() {
        return dne;
    }
}
