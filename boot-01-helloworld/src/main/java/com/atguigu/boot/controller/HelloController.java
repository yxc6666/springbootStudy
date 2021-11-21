package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Car;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    public Car car;
    /*
    1. @ConfigurationProperties(prefix = "mycar") 配置绑定，配合@Compoment
    2. 或者在配置类中，一定要配置类才会进入容器，@EnableConfigurationProperties(Car.class)+
        实体中得@ConfigurationProperties(prefix = "mycar") ，两者功能一样
    3.EnableConfigurationProperties ：开启car配置绑定功能，和自动注入到容器中
    4.一般@Compoment用于自己得，但是引入第三方包，如果没有@Compoment注解，则采用
        第二种方式


     */
    @RequestMapping("/car")
    public Car car(){
        return car;
    }


    @RequestMapping("/hello")
    public String handle01(){
        return "hello springboot2";
    }
}
