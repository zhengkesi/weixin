package com.weixin.publics;

import com.weixin.publics.dto.JsonRootBean;
import com.weixin.publics.dto.User;
import com.weixin.publics.utils.PoiUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

}
