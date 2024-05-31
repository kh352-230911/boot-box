package com.sh.app.auth.controller;

import com.sh.app.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    //기존 login
//    @GetMapping("/login.do")
//    public void login() {
//        System.out.println("AuthController - ");
//    }

    //새로작성한 login
    @GetMapping("/login.do")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model)
    {
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        System.out.println(error+"/"+exception);
        return "auth/login";
    }
    @GetMapping("/adminLogin.do")
    public void adminLogin() {}
}
