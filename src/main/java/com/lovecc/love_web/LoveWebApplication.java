package com.lovecc.love_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LoveWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveWebApplication.class, args);
    }

}
