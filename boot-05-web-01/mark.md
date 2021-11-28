###2 简单功能分析
#### 2.1 静态资源访问
```
1. 静态资源访问
    call /static(or public or /resources or /META-INF/resources）
    以上路径都是可以直接放静态资源
    
    原理：因为它匹配的是/** 所以优先去controller中找，如果没有就去静态资源找，都没有就404
2.改变默认静态资源
    spring.mvc.static-path-pattern=/xxx/**
    访问的时候加/xxx/*.jpg    
3.webjar
https://www.webjars.org/
```
```yaml
改变默认静态资源访问路径
spring:
  mvc:
    static-path-pattern: /res/**
```
```yaml
改变默认静态资源存放路径
spring:
  mvc:
    static-path-pattern: /res/**
  web:
    resources:
      static-locations: classpath:/pic
```
#### 2.2 欢迎页支持
- 静态资源路径下存在index.html
- controller能处理/index路径的

```yaml
如果配置了静态资源访问前缀，默认欢迎页则不生效，图标也不生效
#spring:
#  mvc:
#    static-path-pattern: /res/**

```
```yaml
禁用所有静态资源
spring:
  web:
    resources:
      add-mappings: false

```
### 3. 请求参数处理
#### 3.1 请求映射
```
1. 基本继承或者实现
    DispatcherServlet(doDispatch)->FrameworkServlet(doService)->HttpServletBean->HttpServlet(doGet)
2. 所有请求映射都在HandlerMapping中
    springboot自动配置了RequestMappingHandlerMapping,请求进来会循环所有的HandlerMapping
    看是否有请求信息，如果有，根据路径就找到这个请求的handler(controller)，如果没有就下一个mapping
    
    
```
#### 3.2 基本请求注解
```
1. @PathVariable(路径变量)->获取路径中/{xxx}
2. @RequestHeader(请求头名字)->获取请求头信息，如果没有，获取所有
3. @RequestParam(参数名字)->获取请求参数
4. @CookieValue(cookie名字) ->获取cookie值，没有参数，则全部
5. @RequestBody()   获取请求体[post]的值
6. @RequestAttribute() 从HttpServletRequest 中获取属性值，一般是转发"forward:/路径"
```
32