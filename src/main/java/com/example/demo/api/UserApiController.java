package com.example.demo.api;

import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于测试 MySQL + JPA
 * @author saturn
 * @time 2017/7/7
 * @email vmto@qq.com
 */

@RestController
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    // 查询
    @GetMapping(value = "/api")
    public List<User> userList(){

       return userRepository.findAll();
    }

    // 增加
    @PostMapping(value = "/api")
    public User userAdd(@RequestParam("name") String name,
                              @RequestParam("age") Integer age,
                              @RequestParam("sex") Integer sex){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);

        return userRepository.save(user);
    }

    // 更新
    @PutMapping(value = "/api/{id}")
    public User userPut(User user){
        user.setId(user.getId());
        user.setName(user.getName());
        user.setAge(user.getAge());
        user.setSex(user.getSex());
        return userRepository.save(user);
    }

    // 删除
    @DeleteMapping(value = "/api/{id}")
    public void userDel(@PathVariable("id") Integer id){
        userRepository.delete(id);
    }

}
