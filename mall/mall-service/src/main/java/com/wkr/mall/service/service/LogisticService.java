package com.wkr.mall.service.service;


import com.wkr.admin.common.vo.LogisticsResultVo;

/**
* ExpressService 接口

*/
public interface LogisticService {
    LogisticsResultVo info(String expressNo, String type, String com, String phone);
}
