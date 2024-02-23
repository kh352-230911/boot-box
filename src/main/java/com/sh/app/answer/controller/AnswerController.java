package com.sh.app.answer.controller;

import com.sh.app.answer.dto.AnswerCreateDto;
import com.sh.app.answer.entity.Answer;
import com.sh.app.answer.service.AnswerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/createAnswer.do")
    public void createAnswer() {

    }
    @PostMapping("/createAnswer.do")
    public String createAnswer(@Valid AnswerCreateDto answerCreateDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        answerService.createAnswer(answerCreateDto);

        redirectAttributes.addFlashAttribute("msg", "답변을 작성했습니다.");

        return "redirect:/ask/askList.do";
    }

    @GetMapping("/answerByAskId/{askId}")
    public List<Answer> answerByAskId(@PathVariable Long askId) {
        return answerService.answerByAskId(askId);
    }
}
