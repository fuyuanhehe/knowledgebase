package com.hg.knowledgebase.module.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/test")
public class testController {


    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/NewFile")
    public String NewFile(){
        return "NewFile";
    }



}


