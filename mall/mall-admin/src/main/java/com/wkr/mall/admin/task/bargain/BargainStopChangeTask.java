package com.wkr.mall.admin.task.bargain;

import com.wkr.admin.common.utils.DateUtil;
import com.wkr.mall.service.service.StoreBargainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 砍价活动结束状态变化定时任务
 * @author wkr
 */
@Component
@Configuration
@EnableScheduling
public class BargainStopChangeTask {
    private static final Logger logger = LoggerFactory.getLogger(BargainStopChangeTask.class);

    @Resource
    private StoreBargainService storeBargainService;

    /**
     * 5秒钟同步一次数据
     */
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void init(){
        logger.info("---BargainStopChangeTask------bargain stop status change task: Execution Time - {}", DateUtil.nowDateTime());
        try {
            storeBargainService.stopAfterChange();
        }catch (Exception e){
            logger.error("BargainStopChangeTask exception=" , e);
        }

    }

}
