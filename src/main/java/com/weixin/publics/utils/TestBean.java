package com.weixin.publics.utils;

import com.weixin.publics.dto.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xiaodong
 * @Date: 2020/10/20 15:05
 */
@Configuration
public class TestBean {
    @Bean()
    public User user(){
        return new User();
    }
    /*@Bean
    public void use1(){
        System.out.println(user());
    }*/

}
