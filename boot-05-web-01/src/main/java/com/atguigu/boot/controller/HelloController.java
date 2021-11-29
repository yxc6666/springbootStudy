package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @RequestMapping("/person")
    public Person person(){
        Person person = new Person();
        person.setAge(10);
        person.setUsername("张三");

        return person;
    }
}
