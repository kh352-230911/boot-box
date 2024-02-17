package com.sh.app.ask.service;

import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.repository.AskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AskService {

    @Autowired
    private AskRepository askRepository;

    public List<Ask> findAll() {
        System.out.println("문의조회 service");
        return askRepository.findAll();
    }
}
