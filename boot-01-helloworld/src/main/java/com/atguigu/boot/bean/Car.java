package com.atguigu.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
@ConfigurationProperties(prefix = "mycar") 把java对象和配置
文件绑定，前缀是mycar得，注意配合@Component 加载到容器中，在容器
中得组件才会有springboot各种功能
 */
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}
