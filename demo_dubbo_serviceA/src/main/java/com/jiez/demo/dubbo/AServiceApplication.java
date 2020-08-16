package com.jiez.demo.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author by Jiez
 * @classname Applicationa
 * @description TODO
 * @date 2020/6/21 20:06
 */
@SpringBootApplication
@ImportResource(value = {"classpath:spring/dubbo-provider.xml"})
public class AServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AServiceApplication.class, args);
    }
}
