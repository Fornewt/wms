package org.example.back;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 暂跳过数据库维护。(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("org.example.back.mapper")
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
        System.out.println("后端成功启动！");
    }

}
