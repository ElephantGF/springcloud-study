package com.study.canary.user.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author GAOFENG
 * @create 2019/1/21
 * @description
 */
@RestController
public class UserRest {
    @Value("${node}")
    private String currentNode ;
    @GetMapping("port")
    public String getCurrentPort(HttpServletResponse response){
        return currentNode ;
    }

}
