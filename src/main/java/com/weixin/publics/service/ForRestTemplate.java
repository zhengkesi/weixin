package com.weixin.publics.service;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

/**
 * @Author: xiaodong
 * @Date: 2020/11/6 9:56
 */
public class ForRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    public void setRestTemplate() throws Exception{
        //URL url = new URL("");
        HttpHeaders headers = new HttpHeaders();
        headers.add("","");
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("url", HttpMethod.GET, request, String.class, (Object) null);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
    }
}
