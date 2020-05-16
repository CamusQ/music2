package com.example.music2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping("/hello")
    public String hello(){
        for (int i = 0; i < 5; i++) {
            log.info("测试接口：{}","日志开启");

        }

        return "hello world";
    }



}
