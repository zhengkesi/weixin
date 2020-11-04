package com.weixin.publics.service;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.dto.ThisUser;
import com.weixin.publics.dto.User;

/**
 * @Author: xiaodong
 * @Date: 2020/11/4 10:42
 */
public class ThisUse {

    /*public static void main(String[] args){
        B b = new B(new A());
    }*/
    public static void main(String[] args) {
        //new ThisUser().main();
        User user = new User("1", "2");
        System.out.println(user.toString());
        String string = JSONObject.toJSONString(user);
        JSONObject jsonObject = JSONObject.parseObject(string);
        System.out.println(jsonObject);
        System.out.println(string);
    }

}

class A{
    public A(){
        //this代表当前这个A类
        new B(this).print();  // 匿名对象
    }
    public void print(){
        System.out.println("Hello from A!");
    }
}

class B{
    A a;
    public B(A a){
        this.a = a;
    }
    public void print() {
        a.print();
        System.out.println("Hello from B!");
    }
}