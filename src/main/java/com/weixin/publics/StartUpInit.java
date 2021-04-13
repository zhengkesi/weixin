package com.weixin.publics;


import com.weixin.publics.service.SearcherNewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: xiaodong
 * @Date: 2020/12/14 14:56
 */
@Component
public class StartUpInit  implements ApplicationListener<ApplicationEvent>{

    private static final Logger logger = LoggerFactory.getLogger(StartUpInit.class);

    @Autowired
    private SearcherNewService searcherNewService;

    private static boolean isStart = false;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        try {
            if (! isStart) {
                isStart = true;
                logger.info(" init search data ");
                searcherNewService.index();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}