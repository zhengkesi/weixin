package com.weixin.publics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.weixin.publics")
public class PunlicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PunlicsApplication.class, args);
    }

}
