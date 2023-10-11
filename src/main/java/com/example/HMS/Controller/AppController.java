package com.example.HMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @RequestMapping("/api")
    @ResponseBody
    public String hello(){
        return "Hello You Are In Main Page!";
    }
}
