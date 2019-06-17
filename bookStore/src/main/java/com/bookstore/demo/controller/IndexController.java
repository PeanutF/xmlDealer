package com.bookstore.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IndexController {

    @RequestMapping("/index")
    public int index(){
        return 1;
    }

}
