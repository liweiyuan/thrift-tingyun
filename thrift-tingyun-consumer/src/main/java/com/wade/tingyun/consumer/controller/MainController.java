package com.wade.tingyun.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tingyun on 2017/12/19.
 */
@RestController
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
