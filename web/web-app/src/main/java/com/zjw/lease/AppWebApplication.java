package com.zjw.lease;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 移动端启动类
 *
 * @author 朱俊伟
 * @since 2024/06/05 00:06
 */
@SpringBootApplication
@EnableAsync
@Slf4j
public class AppWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppWebApplication.class, args);
        log.info("http://localhost:8081/doc.html");
        log.info("http://localhost:8081/");
    }
}
