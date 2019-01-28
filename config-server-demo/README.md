spring config 配置中心的最小实例  
一、配置中心服务  
   第一步：Maven依赖
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    
   第二步：启动注解
    
    @SpringBootApplication
    @EnableConfigServer
    public class ConfigServerApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ConfigServerApplication.class, args);
        }   
    }
    
   第三步：配置git仓库
   
    server:
      port: 3344
    
    spring:
      cloud:
        config:
          server:
            git:
              uri: https://github.com/ElephantGF/config-repo
              username: ElephantGF
              password: xxxxxxx
      application:
        name: config-server

二、配置客户端  
    第一步：Maven依赖  
    
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-config</artifactId>
            </dependency>
   
   第二步：配置文件  bootstrap.yml
   
    spring:
      cloud:
        config:
          uri: http://localhost:3344
          profile: test
          name: config-client
          label: master
三、本质  
建立了 git仓库 -> config-server -> config-client 的通信，使得config-client（也就是我们  
每一个服务）可以在运行时读取git仓库中的配置文件。  

四、其他   
官方教程：https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.1.0.RELEASE/single/spring-cloud-config.html
    
    