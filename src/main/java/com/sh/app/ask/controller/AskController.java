package com.sh.app.ask.controller;

import com.sh.app.ask.dto.AskDetailDto;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.service.AskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/ask")
public class AskController {
    @Autowired
    private AskService askService;

    @GetMapping("/askList.do")
    public void ask(Model model) {
        List<Ask> asks = askService.findAll();
        log.debug("asks = {}", asks);
        model.addAttribute("asks", asks);
        System.out.println("문의조회 controller" + asks);
    }

    @GetMapping("createAsk.do")
    public void createAsk() {

    }

    @GetMapping("/askDetail.do")
    public void askDetail(Model model, Long id) {
        AskDetailDto askDetailDto = askService.findById(id);
        model.addAttribute("ask", askDetailDto);
        log.debug("ask = {}", askDetailDto);
    }
}
