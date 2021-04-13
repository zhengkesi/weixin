package com.weixin.publics.dto;

import lombok.Data;
;

/**
 * @Author: xiaodong
 * @Date: 2020/10/20 15:08
 */
@Data
public class User {
    private String name;
    private String age;

    //使用有参构造是必须有个无参构造
    public User() {
    }



    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }


}
