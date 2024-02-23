package com.sh.app.answer.service;

import com.sh.app.answer.dto.AnswerCreateDto;
import com.sh.app.answer.entity.Answer;
import com.sh.app.answer.repository.AnswerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createAnswer(AnswerCreateDto answerCreateDto) {
        Answer answer = convertToAnswer(answerCreateDto);
        answerRepository.save(answer);
    }

    private Answer convertToAnswer(AnswerCreateDto answerCreateDto) {
        return modelMapper.map(answerCreateDto, Answer.class);
    }

    public List<Answer> answerByAskId(Long askId) {
        return answerRepository.findByAskId(askId);
    }
}
