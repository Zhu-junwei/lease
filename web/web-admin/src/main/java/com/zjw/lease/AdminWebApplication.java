package com.zjw.lease;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 后端管理端启动类
 *
 * @author 朱俊伟
 * @since 2024/06/05 00:06
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
        log.info("http://localhost:8080/doc.html");
        log.info("http://localhost:8080/");
    }
}
