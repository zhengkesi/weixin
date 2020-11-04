package com.weixin.publics.service;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.utils.HttpUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaodong
 * @Date: 2020/9/28 10:48
 */
@Service
public class HttpTestService {

    private static Logger logger = LoggerFactory.getLogger(HttpTestService.class);
    private static final List<String> listStatic = new ArrayList<>();
    static {
        listStatic.add("a");
        listStatic.add("b");
        listStatic.add("c");
        listStatic.add("d");
    }

    public JSONObject dotest(){
        JSONObject jsonObject = HttpUtil.doGet("https://v1.hitokoto.cn?c=c");
        return jsonObject;
    }

    public static void main(String[] args) throws Exception{
        Base64 base64 = new Base64();
        String url = "https://v1.hitokoto.cn?c=c";
        String name = "测试";
        //路径编码
        String encode = URLEncoder.encode(url, "utf-8");
        System.out.println(encode);
        //中文字符串编码
        String string = base64.encodeToString(name.getBytes("utf-8"));
        System.out.println(string);
        //中文字符编码后解码
        byte[] decode = base64.decode(string);
        System.out.println(decode);
        String s = new String(decode, "utf-8");
        System.out.println(s);
        //System.out.println(listStatic);
    }

}
