package com.weixin.publics.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.dto.User;
import com.weixin.publics.service.SearcherNewService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiaodong
 * @Date: 2020/9/28 16:00
 */
@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private User user;
    @Autowired
    private SearcherNewService searcherNewService;

    @GetMapping("/token")
    public JSONObject setToken(HttpServletResponse response){
        response.setHeader("token","ssssss");
        Map map = new HashMap();
        map.put("code",200);
        map.put("message","成功");
        String string = JSONObject.toJSONString(map);
        return JSONObject.parseObject(string);
    }

    @GetMapping("/rabbit")
    public String  rabbit(HttpServletResponse response){
        rabbitTemplate.convertAndSend("rabbit_1","","这个是个测试");
        return "发送成功";
    }

    @GetMapping("/bean")
    public String  bean(String id){
        user.setName(id);
        user.setAge("2");
        return user.toString();
    }

    @GetMapping("/user")
    public Map user(){
        User user = new User("1", "2");
        String string1 = user.toString();
        String string = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(string);
        Map map = new HashMap();
        //"User(name=1, age=2)"
        map.put("string1",string1);
        // "{\"age\":\"2\",\"name\":\"1\"}"
        map.put("string",string);
        //{"name":"1","age":"2"}
        map.put("jsonObject",jsonObject);
        return map;
    }

    @GetMapping("/getSearch")
    public Object getSearch(){
        try {
            Object search = searcherNewService.search("测试校");
            //String string = JSONObject.toJSONString(search);
            return search;
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
