package com.example.configclient.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GAOFENG
 * @create 2019/1/28
 * @description
 */
@RestController
public class Hello {
    @Value("${spring.application.name}")
    private String serviceName ;
    @RequestMapping("serviceName")
    public String getServiceName(){
        return serviceName ;
    }
}
