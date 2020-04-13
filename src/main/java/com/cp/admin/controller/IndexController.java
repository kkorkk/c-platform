package com.cp.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping("")
    public String defaultIndex(){
        return "login";
    }

    @GetMapping(value = {"index","index/"})
    public String index(){
        return "index";
    }

    @GetMapping("indexMain")
    public String indexMain(){
        return "index_main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

}
