package com.sh.app.answer.service;

import com.sh.app.answer.entity.Answer;
import com.sh.app.answer.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAll() {
        System.out.println("문의조회 service");
        return answerRepository.findAll();
    }
}
