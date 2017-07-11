package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 数据表user实体类
 * @author saturn
 * @time 2017/7/7
 * @email vmto@qq.com
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer age;
    private Integer sex;

    // get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSex() {
        return sex;
    }

    // set
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
