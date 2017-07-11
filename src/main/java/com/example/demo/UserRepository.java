package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * user接口
 * @author saturn
 * @time 2017/7/7
 * @email vmto@qq.com
 */

public interface UserRepository extends JpaRepository<User,Integer> {

}
