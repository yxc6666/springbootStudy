package com.atguigu.boot.config;

import com.atguigu.boot.bean.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    //1.webMvcConfigurer 定制springmvc的功能
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return  new WebMvcConfigurer() {
            //参数解析格式定制，例如将'a,18'转换成对应实体中name=a age=18
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String , Pet>() {

                    //将字符串中的"asdf,20"-> name:adf,weight:20
                    @Override
                    public Pet convert(String source) {
                        if(StringUtils.isEmpty(source)){
                            String[] split = source.split(",");
                            Pet pet = new Pet();
                            pet.setName(split[0]);
                            pet.setWeight(Double.valueOf(split[1]));
                            return pet;
                        }


                        return null;
                    }
                });
            }
        };
    }
}
