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
2,默认在底层配好所有配置，如果用户自己配置了，以用户自己配置优先
@Bean
    @ConditionalOnMissingBean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.properties.getCharset().name());
        filter.setForceRequestEncoding(this.properties.shouldForce(org.springframework.boot.web.servlet.server.Encoding.Type.REQUEST));
        filter.setForceResponseEncoding(this.properties.shouldForce(org.springframework.boot.web.servlet.server.Encoding.Type.RESPONSE));
        return filter;
    }
    可以看到@ConditionalOnMissingBean，如果没有配置，则生效
```
总结：
```angular2html
1.springboot先加载所有自动配置 xxxAutoConfiguration
2.每个自动配置类按照条件进行生效，默认都会在properties进行绑定
3.如果生效得配置类就会给容器中装配很多得组件
4.只要容器中有这些组件相当于这些功能就有了
5.只要用户有自己配置得，就以用户自己得优先
6.定制化配置两种方法
    6.1 用户直接自己@bean替换底层组件
    6.2 用户去看这个组件获取配置文件什么值就去properties中修改(更常用)
例子：
@ConditionalOnProperty(
    prefix = "server.servlet.encoding",
    value = {"enabled"},
    matchIfMissing = true
)
字符集编码修改得话，在properties中以server.servlet.encoding为前缀修改即可
7.大概流程xxxAutoConfiguration->配置组件->从xxxProperties中获取值->从xxx.properties中获取用户自定义值


```
### 5 最佳实践
- 引入场景依赖
    - [starter](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/using-spring-boot.html#using-boot-starter)
- 查看自动配置了那些
    - 在properties中debug=true(默认是false) 控制台中会告诉你哪些开启那些没开启Positive:生效，negative：未生效
- 是否需要修改
    - 参照文档修改配置
        - [properties](https://docs.spring.io/spring-boot/docs/2.4.13/reference/html/appendix-application-properties.html#common-application-properties)
        - 自己分析,xxxProperies绑定了那些属性
    - 自定义加入或者替换组件
        - @Bean，@Component
    - 自定义器 xxxCustomizer
### 6 开发小技巧
```angular2html
1.lombok 
    一般starter都会有lombok，引入依赖，idea装上lombok即可
    spring-boot-starter-parent->org.springframework.boot 搜索
2. devtools
    自动重启
3. Spring initailizr

```
