## 创建JavaWeb框架

### 1.创建web项目

- 使用骨架即项目模板来创建  选中 从archetype创建
- 选中 Maven-archetype-webapp 模板来创建
- ![image-20220425201333975](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220425201333975.png)
- 删除pom.xml中的多余的坐标
- 补齐缺失的目录结构
  - 在main目录下创建 **java** 和 **resources** 文件

### 2.在pom.xml文件中 导入jar依赖包 和 插件

项目名称

```xml
<groupId>org.example</groupId>
<artifactId>exam_1.0</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>war</packaging>
```

导入Jar包

```xml
 <dependencies>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.6 </version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

<!--    Spring-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>5.3.16</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.16</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.16</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.16</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.16</version>
    </dependency>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.9.9.1</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.3.16</version>
    </dependency>
<!--    Mvc-->
    <!-- JSON-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.13.2</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.13.2.2</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.13.2</version>
    </dependency>

    <!-- Servlet-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>

<!--    Mybatis-->
    <!--JDBC-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
    </dependency>
    <!--德鲁伊连接池-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.2.5</version>
    </dependency>
    <!--Mybatis框架-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.5</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.7</version>
    </dependency>

  </dependencies><!--IO流 的工具类 其中可以提供copy方法 来进行输出流的copy-->
<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.6 </version>
</dependency>
<!--Spring 框架-->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.3.16</version>
</dependency>
<!--JDBC-->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.28</version>
</dependency>
<!--JUNIT-->
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13.1</version>
</dependency>
<!--德鲁伊连接池-->
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid</artifactId>
  <version>1.2.5</version>
</dependency>
<!--Mybatis框架-->
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.5</version>
  <!--导入servlet-->
</dependency>
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.1.0</version>
  <scope>provided</scope>
</dependency>
JSTL
<dependency>
  <groupId>jstl</groupId>
  <artifactId>jstl</artifactId>
  <version>1.2</version>
</dependency>
```

插件------启动Tomcat服务器

```xml
    <build>
      <plugins>
        <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
          <configuration>
            <!--        修改端口号-->
            <port></port>
            <!--        修改访问的url-->
            <path></path>
          </configuration>
        </plugin>
      </plugins>
    </build>
```

### 3.建立项目的三层框架结构

- 在resources中创建mybatis-config.xml 用于来配置数据源 以及导入mapper.xml文件
- ![image-20220319170211648](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220319170211648.png)
- 注意   resources中的文件目录创建 是  com/spring/    不是用.来间隔

## Spring

1.导入依赖坐标即jar包

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.3.16</version>
</dependency>
```

2.核心配置文件 applicationContext.xml  

命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">
    <context:component-scan base-package="com.exam_2">
        <!--    排除controller-->
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
<!--    加载德鲁伊配置文件-->
    <context:property-placeholder location="classpath:druid.properties"/>
    <bean id="dataSource" class="${dataSource}">
        <property name="driverClassName" value="${driverClassName}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
    </bean>
<!--    配置mybatis的SqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>
<!--    扫描mapper所在包 实现mapper并放到容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.exam_2.dao"></property>
    </bean>
</beans>
```

如果用注解开发的话，不要忘记使用组件扫描！

### xml中配置JDBCTemplate    Mybatis???

### 集成JUNIT

若用注解开发使用以往的JUnit的话 会报空指针异常

1. 导入Spring 集成 Junit坐标

   ```xml
   <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-test</artifactId>
     <version>5.3.16</version>
   </dependency>
   ```

2. 使用@Runwith 注解替换原来的运行期

3. 使用@ContextConfiguration 指定配置文件或配置类

4. 使用 @Autowired注入需要测试的对象

5. 测试

#### 注意

```xml
spring-test  与  spring-context  版本号要一致
```

否则Test的时候报错

### 集成Web环境

#### Spring提供获取应用上下文的工具

Spring 提供一个监听器 **ContextLoaderListener** 监听器内部加载Spring配置文件，创建应用上下文对象，并存储到ServletContext域中，提供一个客户端工具**WebApplicationContextUtils**    供使用者获得应用上下文对象。

1.导入坐标 Spring-Web

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-web</artifactId>
  <version>5.3.16</version>
</dependency>
```

2.web.xml中配置

```xml
<!--  全局化初始参数-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
```

![image-20220504100513510](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220504100513510.png)

3.test

![Spring集成web](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220502220931822.png)

### 启动

#### Tomcat-7-run 插件

1.pom.xml 导入

```xml
<build>
  <finalName>Spring3.0</finalName>
  <plugins>
    <plugin>
      <groupId>org.apache.tomcat.maven</groupId>
      <artifactId>tomcat7-maven-plugin</artifactId>
      <version>2.2</version>
      <configuration>
        <!--        修改端口号-->
        <port></port>
        <!--        修改访问的url-->
        <path></path>
      </configuration>
    </plugin>
  </plugins>
</build>
```

2.使用

<img src="C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426221659345.png" alt="image-20220426221659345" style="zoom:50%;" />

####  IDEA启动![image-20220426221736420](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426221736420.png)

1.

![image-20220426221805728](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426221805728.png)

2.

![image-20220426221833702](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426221833702.png)

3.基本信息

![image-20220426221905895](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426221905895.png)

2.部署War包  或者

war包：打成war置于服务器运行，这种方式我们比较常见。
war exploded：将web工程以当前文件夹的位置关系上传到服务器。就是直接把文件夹、jsp页面 、classes等移到Tomcat 部署文件夹里面，进行加载部署。因此这种方式支持热部署，一般在开发的时候也是用这种方式。
![image-20220426222035742](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220426222035742.png)

注意应用程序上下文------**--项目名称**



## Error creating bean with name 'bookController': 

描述：Unsatisfied dependency expressed through method 'setBookService' parameter 0; nested exception isorg.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.spring_3.service.bookService.

原因：web.xml: 看Spring框架启动时使用的监听器没配置

```xml
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

## SpringMVC

1.导入坐标  spring-webmvc

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>5.3.16</version>
</dependency>
```

2.建立spring-mvc.xml文件   配置组件扫描  加入context命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <!--    Controller层的组件扫描-->
    <context:component-scan base-package="com.exam_2.controller"></context:component-scan>

    <!--对静态资源的访问  -->
    <mvc:default-servlet-handler />
    <mvc:annotation-driven/>

    <!--配置处理器映射器-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters">
            <list>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                </bean>
            </list>
        </property>
    </bean>
    <!--内部资源视图解析器-->
    <!--    拦截器-->
    <!--    <mvc:interceptors>-->
    <!--        <mvc:interceptor>-->
    <!--            <mvc:mapping path="/html/index.html"/>-->
    <!--            <bean class="com.spring_3.interceptor.Interceptor"/>-->
    <!--        </mvc:interceptor>-->
    <!--    </mvc:interceptors>-->
```

3.web.xml

```xml

  <!--  全局化初始参数-->
  <!--    spring 监听器-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <!--乱码过滤器-->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--  配置SpringMvc的前端控制器-->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <!--  load on Start 代表服务器启动时就加载-->
    <load-on-startup>1</load-on-startup>
  </servlet>

  <!--  配置拦截的URL-->
  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping><!--  配置SpringMvc的前端控制器-->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
<!--  告诉控制器 其配置文件在哪-->
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
<!--  load on Start 代表服务器启动时就加载-->
    <load-on-startup>1</load-on-startup>
  </servlet>
<!--  配置拦截的URL-->
  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```

### **回写数据（对象 集合）自动转为JSON数据**

1.为处理器适配器配置消息转换参数，指定使用jackson进行对象或集合的转换。

web.xml

```xml
<!--配置处理器映射器-->
    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
        <property name="messageConverters">
            <list>
                <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">

                </bean>
            </list>
        </property>
    </bean>
```

2.通过mvc注解

![image-20220503161618050](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220503161618050.png)

### 静态资源无法访问

描述： 只能打开和跳转  .jsp  文件  无法访问  .html 文件  和  其他 css 图片等文件

原因：

![image-20220503150708807](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220503150708807.png)

当你的项目中使用了"/"， 它会替代tomcat 中的default。导致所有的静态资源都给DispatcherServlet处理，默认情况下DispatcherServlet没有处理静态资源的能力。没有控制器对象能处理静态资源的访问。所以静态资源( html，js，图片， css)都是404。动态资源some.do是可以访问，的因为我们程序中有MyController控制器对象，能处理some.do请求。
配置会把所有的请求都会进行拦截，交给spring去处理。而spring所有请求的URL都是在controller中使用注解@RequestMapping标明，所以这样的情况下访问静态资源是访问不到的。

解决方式：

1.

![img](https://img-blog.csdnimg.cn/20210511215128622.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hkc0hEUzY=,size_16,color_FFFFFF,t_70)

2.

![2](C:\Users\HP\Desktop\2.png)

但如果文件夹多的话需要一个一个去创建  比较繁琐  故使用第一种

### axios POST请求报错

描述：Uncaught (in promise) Error: Request failed with status code 415以及400 (Bad Request) 

前后端传送数据的格式不一样，后端要求的是json格式的数据，即**‘Content-Type’:‘application/json;charset=UTF-8’，而前端的请求头是：‘Content-Type’:‘application/x-www-form-urlencoded’**

解决方式： 在Axios发送请求时加上

```html
headers : {'Content-Type':'application/json;charset=UTF-8'},
```

------- 将数据手动转为JSON      data: JSON.stringify(data),    好像没啥用

### 解决乱码问题   

#### 请求数据

#### 响应数据

![image-20220505092025446](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20220505092025446.png)

## Mybatis

1。xml

只剩下别名

## Web.xml

```xml

```
