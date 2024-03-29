package com.sh.app.answer.repository;

import com.sh.app.answer.entity.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswerByAskId(Long askId);

//    List<Answer> findByAskId(Long askId);
}
