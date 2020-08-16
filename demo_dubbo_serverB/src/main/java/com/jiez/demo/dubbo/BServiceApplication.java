package com.jiez.demo.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * @author by Jiez
 * @classname Application
 * @description TODO
 * @date 2020/6/21 19:38
 */
@SpringBootApplication
@ImportResource(value = {"classpath:spring/dubbo-consumer.xml"})
public class BServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BServiceApplication.class, args);
    }
}
