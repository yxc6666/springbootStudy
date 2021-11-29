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
7. 自定义转换器用于参数的转换，可以通过mvcConfig
```
--------------------------------------------------------------
### 4 数据响应
#### 4.1 响应json
``@ResponseBody注解即可``
```
1.web start
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
</dependency>

        
2. <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.0</version>
      <scope>compile</scope>
</dependency>
<dependency>
      <groupId>com.fasterxml.jackson.datatype</groupId>
      <artifactId>jackson-datatype-jdk8</artifactId>
      <version>2.13.0</version>
      <scope>compile</scope>
</dependency>
3.已经自动配好了处理为json，所以直接用@ResponseBody即可
```
#### 4.2 返回值解析器
RequestMappingHandlerAdapter类中invokeHandlerMethod方法中参见argumentResolvers参数解析或者returnValueHandlers响应解析
```
1. 返回值处理器判断是否支持这种类型的返回值supportsReturnType
2. 返回值处理器调用handleReturnValue进行处理
3. ResquestResponseBodyMethodProcessor 可以处理返回值标了@ResponseBody
    注解的
    3.1 利用MessageConverters 消息转换器进行处理，将数据写为json
        3.1.1 内容协商（请求头中的accept）
        3.1.2 服务器会根据自身的能力，决定什么类型的数据
        3.1.3 SpringMvc会遍历容器底层的 HttpMessageConverter,看谁能处理
        3.1.4 一般json通过MappingJackson2HttpMessageConverter写出去 

```
#### 4.3 支持返回类型
- ModelAndView
- Model
- View
- ResponseEntity
- ResponseBodyEmitter
- StreamResponseBody
- HttpEntity
- HttpHeaders
- Callable (判断是不是异步的)
- DeferredResult 
- ListenableFuture
- CompletionStage
- webAsyncTask
- 有@ModelAttribute 注解的
- 有@ResponseBody 注解 处理器为RequestResponseBodyMethodProcessor

#### 4.4 HttpMessageConverter原理(消息转换器)
```
boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);

boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);
1.将元类型clazz -> mdeiaType

2.默认支持的
    1. String
    2. Resource
    3. ResourceRegin
    4. DOMSource.class \ SAXSource.class 等等
    5. MutiValueMap
    6. 对于json 直接返回true
    
```
39
