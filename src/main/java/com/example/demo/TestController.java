package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 用于测试 freemarker
 * @author saturn
 * @time 2017/7/7
 * @email vmto@qq.com
 */

@Controller
public class TestController {

    @GetMapping("/test")
    public String user(Map<String, Object> map){

        map.put("name","李白");
        map.put("age",22);
        map.put("sex",1);//0:女、1:男

        return "test";
    }

}
