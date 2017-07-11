package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * user控制器
 * @author saturn
 * @time 2017/7/7
 * @email vmto@qq.com
 */

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user")
    public String user(Map<String,Object> map){

        map.put("items",userRepository.findAll());

        return "user";
    }

}
