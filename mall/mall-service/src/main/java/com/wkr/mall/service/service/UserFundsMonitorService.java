package com.wkr.mall.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wkr.admin.common.request.PageParamRequest;
import com.wkr.admin.common.vo.UserFundsMonitor;
import com.github.pagehelper.PageInfo;
import com.wkr.admin.common.request.BrokerageRecordRequest;
import com.wkr.admin.common.model.user.UserBrokerageRecord;

/**
*  UserRechargeService 接口

*/
public interface UserFundsMonitorService extends IService<UserFundsMonitor> {

    /**
     * 佣金记录
     * @param request 筛选条件
     * @param pageParamRequest 分页参数
     * @return PageInfo
     */
    PageInfo<UserBrokerageRecord> getBrokerageRecord(BrokerageRecordRequest request, PageParamRequest pageParamRequest);
}
