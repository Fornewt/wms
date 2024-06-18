package org.example.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 该CorsConfig类通过实现WebMvcConfigurer接口并重写addCorsMappings方法，为整个应用程序配置了CORS策略：
// 路径匹配: 应用所有路径的CORS配置。
// 允许来源: 允许所有来源（*）。
// 允许方法: 允许所有HTTP方法（*）。
// 允许头部: 允许所有请求头（*）。
// 凭证携带: 禁止携带凭证。
// 缓存时间: 预检请求的缓存时间为3600秒（1小时）
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);



    }

}
