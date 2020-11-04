package com.weixin.publics.service;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.dto.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiaodong
 * @Date: 2020/10/26 14:30
 */
public class JsonStringAndToString {


    //测试toString()和toJSONString()的区别
    public static void main(String[] args) {
        User user = new User();
        user.setName("1");
        user.setAge("20");
        Map map = new HashMap();
        map.put("code","200");
        map.put("date",user);
        String string = map.toString();
        //JSONObject jsonObject = JSONObject.parseObject(string2);
        //String string = jsonObject.toString();
        String string1 = JSONObject.toJSONString(map);
        System.out.println(string);
        System.out.println(string);

    }

}
