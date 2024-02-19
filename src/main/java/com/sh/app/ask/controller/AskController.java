package com.sh.app.ask.controller;

import com.sh.app.ask.service.AskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/ask")
public class AskController {
    @Autowired
    private AskService askService;

    @GetMapping("createAsk.do")
    public void createAsk() {

    }

//    @PostMapping("/createAsk.do")
//    public String createAsk(){@Valid
//
//    }
}
