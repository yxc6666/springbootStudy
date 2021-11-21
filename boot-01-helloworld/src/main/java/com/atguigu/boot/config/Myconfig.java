package com.atguigu.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/*
1.配置类，默认都是单实例组测
2.配置类本身也是一个组件
3.proxyBeanMethods：代理bean得方法，默认为true，如果外部调用配置类中得方法，如果为
true，因为是单例模式，所以多次调用对象是相等得，从容器中获取，如果为false则就是一个
简单得方法，所以多次调用是不相等得

4.@Import({User.class,DBHelper.class}) 会给容器中自动创建这两种类型得组件,默认组件名字
就是全类名

5.条件装配 @ConditionalOnBean(name = "asdf")//有asdf才会注册user01,如果放在
方法上则表示容器里有asdf才会注册某个组件，如果放在方法上
表示示容器里有asdf才会注册改配置类下所有bean

6.@ImportResource("资源路径")//导入原来spring配置文件
例如xml配置 ，使其生效
 */

@Configuration(proxyBeanMethods = false)//配置类，默认都是单实例组测,
@Import({User.class,DBHelper.class})
public class Myconfig {
    /*
    给容器中添加组件，一方法名作为组件id，返回类型就是组件类型,返回得值就是
    组件在容器中保存得对象，当然，@Bean("自定义名字") 也是可以得

     */
    @Bean

    public User user01(){
        return new User("zhangsan",18);
    }
    @Bean
    public Pet pet01(){
        return new Pet("tomcat");
    }
}
