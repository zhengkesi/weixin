package com.weixin.publics;

import com.weixin.publics.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PunlicsApplication.class)
class PunlicsApplicationTests {
    @Autowired
    private User user;
    @Test
    void contextLoads() {
        user.setName("1");
        System.out.println(user.getName());
    }

}
