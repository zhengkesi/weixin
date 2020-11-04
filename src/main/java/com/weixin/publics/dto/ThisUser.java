package com.weixin.publics.dto;

import com.weixin.publics.service.ThisUse;
import lombok.Data;

/**
 * @Author: xiaodong
 * @Date: 2020/11/4 15:16
 */
@Data
public class ThisUser {
    private User user;

    public ThisUser(){

    }

    public ThisUser(User user){
        this.user = user;
    }

    public void main(Object o){
        o = user;
        User u = (User) o;
        String name = u.getName();

        System.out.println(name);
    }

    public  void main() {
        User user = new User("1", "2");
        //this代表当前这个类
        new ThisUser(user).main(this);
    }
}
