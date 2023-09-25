package com.wkr.mall.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wkr.admin.common.model.wechat.WechatExceptions;

/**
 *  微信异常服务类

 */
public interface WechatExceptionsService extends IService<WechatExceptions> {

    /**
     * 删除历史日志
     */
    void autoDeleteLog();
}
