package com.sh.app.answer.service;

import com.sh.app.answer.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
}
