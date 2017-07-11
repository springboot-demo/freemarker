### 目录结构
<pre>
├── target                              // 编译目录
├── src                                 // 源码
│   ├── test                            // 单元测试
│   └── main                            // 路由汇总
│       ├── java
│       │   ├───Application             // 启动类
│       │   ├───TestController          // 测试freemarker的控制器
│       │   ├───User                    // 数据库表User的实体类
│       │   ├───UserRepository          // 接口封装
│       │   ├───UserController          // 测试mysql&jpa的控制器
│       │   └───UserApiController       // 测试mysql&jpa的对外接口
│       └─── resources
│           ├── static                  // 静态资源目录
│           ├── templates               // 模板目录
│           └── application.properties  // 配置文件
├── pom.xml                             // maven包管理
├── README.md                           // 项目描述
</pre>

### 第一步：先配置 application.properties
```bash
# product
server.port=8080
server.context-path=/

# freemarker
spring.freemarker.allow-request-override=false
spring.freemarker.cache=true
spring.freemarker.check-template-location=true
spring.freemarker.charset=UTF-8
spring.freemarker.content-type=text/html
spring.freemarker.expose-request-attributes=false
spring.freemarker.expose-session-attributes=false
spring.freemarker.expose-spring-macro-helpers=false

# mysql & jpa
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql = true
```

### 第二步：验证freemarker
TestController
```bash
package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class UserController {
    @GetMapping("/test")
    public String user(Map<String, Object> map){
        map.put("name","李白");
        map.put("age",22);
        map.put("sex",1);//0:女、1:男
        return "user";
    }
}
```

templates/test.ftl
```html
<p>姓名：${name}</p>
<p>年龄：${age}</p>
<p>性别：<#if sex==0> 女 <#elseif sex==1> 男 <#else> 保密 </#if></p>
```
访问查看 localhost:8080/test


### 第三步：验证mysql+jpa

##### 准备工作
创建数据库表
```mysql
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `age` tinyint(3) DEFAULT '18',
  `sex` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8
```

创建3个文件
- [x] User
- [x] UserRepository
- [x] UserController

#### 编写[数据库表]的实体类
main\java\com\example\demo\User
```bash
package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private Integer age;
    private String sex;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
```

#### 编写[Repository]接口
main\java\com\example\demo\UserRepository
```bash
package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
```

#### 编写Controller
main\java\com\example\demo\UserController
```bash
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class UserListController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user")
    public String userList(Map<String,Object> map){

        map.put("items",userRepository.findAll());

        return "userList";
    }
}
```

#### 编写模板文件
templates/user.ftl
```html
<table border="0" cellpadding="0" cellspacing="0">
  <tr>
    <th>ID</th>
    <th>姓名</th>
    <th>年龄</th>
    <th>性别</th>
  </tr>
<#list items as item>
  <tr>
    <td>${item.id}</td>
    <td>${item.name}</td>
    <td>${item.age}</td>
    <td><#if item.sex=="0"> 女 <#else> 男 </#if></td>
  </tr>
</#list>
</table>
```
访问查看 localhost:8080/user

#### 在前后端分离的时代，我倾向于RESTful API与[前端模板]进行交互。
main\java\com\example\demo\api\UserApiController
```bash
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/api")
    public List<User> userList(){

        return userRepository.findAll();
    }
}
```
访问查看 localhost:8080/api