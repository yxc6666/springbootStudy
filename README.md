# springbootStudy
springboot学习

## 自动配置 
```java
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {}
```
### 1.@SpringBootConfiguration
    @Configuration代表当前是一个配置类
### 2.@ComponentScan 
    扫描指定组件
### 3.@EnableAutoConfiguration
```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {}
```
#### 3.1 @AutoConfigurationPackage 指定包规则
```java
@Import({Registrar.class})//给容器中导入组件
public @interface AutoConfigurationPackage {}
//利用Registrar给容器中把main包下所有组件批量注册
```
#### 3.2 AutoConfigurationImportSelector
```
1. getAutoConfigurationEntry(annotationMetadata),给容器
中导入一批组件
2. List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
获取所有需要导入到容器中得组件
3. 利用工厂加载 List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
得到所有组件
4.从META-INF/spring.factories位置加载一个文件，默认扫描我们系统
里面所有META-INF/spring.factories位置得文件
5.最核心得是spring-boot-autoconfiguration 里面得META-INF/spring.factories

```
### 4 按需开启自动配置
```
1.虽然配置项默认都加载但是因为有条件装配 @ConditionalOnXXX(name = "XXX")
所以不配置的是不会加载得
```

