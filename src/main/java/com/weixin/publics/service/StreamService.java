package com.weixin.publics.service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xiaodong
 * @Date: 2020/10/30 9:37
 */
public class StreamService {

    public static void main(String[] args){
        List list = Arrays.asList(1,1,3,2,4,32,22,12,11,33,33);
        Map<String,Object> map = new HashMap();
        map.put("1",1);
        map.put("2",2);
        Object collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
        List<String> collect1 = map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println(collect1);
    }

}
