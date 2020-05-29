package com.lovecc.love_web.controller;

import com.lovecc.love_web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/register")
    public String reg(User user){
        return "register";
    }
    @RequestMapping("/registerHas")
    public String regH(User user){
        return "registerHas";
    }
}
