package com.sh.app.ask.controller;

import com.sh.app.ask.dto.AskDetailDto;
import com.sh.app.ask.dto.CreateAskDto;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.service.AskService;
import com.sh.app.auth.vo.MemberDetails;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/ask")
public class AskController {
    @Autowired
    private AskService askService;

//    @GetMapping("/askList.do")
//    public void ask(Model model) {
//        List<Ask> asks = askService.findAll();
//        log.debug("asks = {}", asks);
//        model.addAttribute("asks", asks);
//        System.out.println("문의조회 controller" + asks);
//    }

    @GetMapping("createAsk.do")
    public void createAsk() {

    }

    @GetMapping("/askDetail.do")
    public void askDetail(Model model, Long id) {
        AskDetailDto askDetailDto = askService.findById(id);
        model.addAttribute("ask", askDetailDto);
        log.debug("ask = {}", askDetailDto);
    }
    @PostMapping("/createAsk.do")
    public String createAsk(@Valid CreateAskDto createAskDto,
                            BindingResult bindingResult,
                            @AuthenticationPrincipal MemberDetails memberDetails,
                            RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.debug("createAskDto = {}", createAskDto);
        createAskDto.setMemberId(memberDetails.getMember().getId());
        askService.createAsk(createAskDto);

        redirectAttributes.addFlashAttribute("msg", "😊문의사항을 성공적으로 보냈습니다. 신속한 답변드리겠습니다.^^");
        return "redirect:/ask/createAsk.do";
    }
}
