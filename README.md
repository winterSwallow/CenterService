# CenterService
一个用于获取微信第三方请求次数和过期时间受限的AccessToken并存储到Redis，提供外部接口Api供前端快速访问的服务

一、项目框架
===
SpringBoot + Redis  

二、项目背景
===
详情参见：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html  

三、项目说明
===
（1）AccessToken.java		凭证对象  
（2）BaseResponse.java		封装返回前端Json对象  
（3）Constant.java			常量配置  
（4）AuthController.java		获取凭证控制器  
（5）RedisConfig.java		Redis过期Key监听注入配置  
（6）RedisKeyExpiredListener.java	Redis过期Key监听回调  
（7）RestTemplateConfig.java		RestTemplate注入配置  
（8）AuthService.java		获取凭证服务  

四、配置说明
===
1、Constant.java类中注解  

（1）生产环境：@PropertySource("file:config/application.properties") 

（2）开发环境：@PropertySource("classpath:application.properties")   

2、pom.xml文件  

（1）开发环境注释掉如下内容，生产环境需取消

```
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <excludes>
                    <exclude>**/*.properties</exclude>
                    <exclude>**/*.yml</exclude>
                </excludes>
            </resource>
        </resources>
```

