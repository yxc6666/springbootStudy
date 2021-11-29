package com.atguigu.boot.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String username;
    private String sss;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String [] intereses;
    private List<String> animal;
    private Map<String,Object> score;
    private Set<Double> salarys;
    private Map<String,List<Pet>> allPets;
}
