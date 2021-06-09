package com.weixin.publics;

import com.alibaba.fastjson.JSONObject;
import com.weixin.publics.dto.JsonRootBean;
import com.weixin.publics.dto.User;
import com.weixin.publics.model.EduFamilyDTO;
import com.weixin.publics.utils.PoiUtils;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PunlicsApplication.class)
class PunlicsApplicationTests {
    @Autowired
    private User user;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpServletRequest request;

    @Test
    void contextLoads() {
        user.setName("1");
        System.out.println(user.getName());
    }
    @Test
    void test(){
        /*JsonRootBean jsonRootBean = new JsonRootBean();
        jsonRootBean.setBJ("1");
        jsonRootBean.setCity("合肥市");
        jsonRootBean.setGradeName("高中三年级");
        jsonRootBean.setIsAudit(0);
        jsonRootBean.setId(230);
        jsonRootBean.setNJ("H3");
        jsonRootBean.setOpenId("oXpf74q46WGQFSD1UTsQA3OgKrYk");
        jsonRootBean.setPhone("15952039359");
        jsonRootBean.setSchId(100075);
        jsonRootBean.setStuId("f26f85c991e94aedb0bb3c306fe4ca83");
        jsonRootBean.setStuName("陈晨");
        jsonRootBean.setUnionId("oYVC105V0m1zUX5VK8o5OVAX0Bzw");
        jsonRootBean.setXXMC("皖新中学");

        request.getSession().setAttribute("user",jsonRootBean);*/
        String url = "http://localhost:8780/stuInfor/getProvince?code=000000";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token","cmMbfTlsoiOYNjcz7z7F+g==");
        HttpEntity<String> httpEntity = new HttpEntity(null,headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        String body = responseEntity.getBody();
        System.out.println(body);
    }
    @Test
    public void test2(){
        PoiUtils.getExcel();
    }

    @Test
    public void test3(){
        String url = "http://live.lead-reading.cn/book-subscrib/stuInfor/auditInformationAgree";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token","AWGeXcILXDyYNjcz7z7F");
        String string = JSONObject.toJSONString(new EduFamilyDTO());
        HttpEntity<String> entity = new HttpEntity<>(string, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String body = exchange.getBody();
        System.out.println(body);
    }

    @Test
    public void test4(){
        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost("http://live.lead-reading.cn/book-subscrib/stuInfor/auditInformationAgree");


            String jsonString = JSONObject.toJSONString(new EduFamilyDTO());

            StringEntity entity = new StringEntity(jsonString, "UTF-8");

            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
            httpPost.setEntity(entity);

            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
            httpPost.setHeader("token", "AWGeXcILXDyYNjcz7z7F");
            // 响应模型
            CloseableHttpResponse response = null;
            try {
                // 由客户端执行(发送)Post请求
                response = httpClient.execute(httpPost);
                // 从响应模型中获取响应实体
                org.apache.http.HttpEntity responseEntity = response.getEntity();
                System.out.println(response.getStatusLine().getStatusCode());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

        }
    }
}
