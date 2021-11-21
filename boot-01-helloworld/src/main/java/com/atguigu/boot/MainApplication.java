package com.atguigu.boot;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.Myconfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages= "com.atguigu")
//默认扫描主程序所在包及其子包scanBasePackages 指定扫描路径
public class MainApplication {
    public static void main(String [] args){
        //1.返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //2.查看容器里面组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String s : beanDefinitionNames) {
            System.out.println(s);
        }

        //3.从容器中获取组件，注册的时候默认都是单实例得

        User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);

        System.out.println(user01 == user02);//因为是单实例，所以获取多少次都是相等得
        //@Configuration 配置类本身也是一个组件
        Myconfig myconfig = run.getBean( Myconfig.class);
        System.out.println("配置类本身也是一个组件：" + myconfig);

        /*@Configuration(proxyBeanMethods = false) 不相等，就是普通对象得方法,true相等
        从容器中获取，springboot 总会保持组件单实例，去容器中查找
        proxyBeanMethods = false lite(轻量级模式)
        proxyBeanMethods = true full(全模式)
        主要解决组件依赖问题和加载速度问题
        最佳实践: 如果配置类中得实例没有外部依赖，仅仅是实例化则false，减少判断
        如果有依赖,例如user有一个pet，为false两次条用user，他的猫不相等，这种存在依赖
        则为true

        */
        User user03 = myconfig.user01();
        User user04 = myconfig.user01();

        System.out.println("proxyBeanMethods: " + (user03 == user04));

        /*
        按类型获取组件
         */
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println("按类型获取组件：" + s);
        }

        /*
        @Conditional 注解，满足条件才会注册

         */
        boolean asdf = run.containsBean("asdf");
        System.out.println("容器中存在asdf: " + asdf);


    }
}
