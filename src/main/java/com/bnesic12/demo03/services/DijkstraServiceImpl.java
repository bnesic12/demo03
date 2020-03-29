package com.bnesic12.demo03.services;

import com.bnesic12.demo03.dto.DNetEntity;
import org.springframework.stereotype.Component;

@Component
public class DijkstraServiceImpl implements IDijkstraService {
    public DNetEntity getDNet() {
        return new DNetEntity();
    }
}
