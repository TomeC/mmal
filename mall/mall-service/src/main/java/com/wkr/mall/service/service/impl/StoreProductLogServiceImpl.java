package com.wkr.mall.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wkr.admin.common.model.log.StoreProductLog;
import com.wkr.mall.service.dao.StoreProductLogDao;
import com.wkr.mall.service.service.StoreProductLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * StoreProductLogServiceImpl 接口实现

 */
@Service
public class StoreProductLogServiceImpl extends ServiceImpl<StoreProductLogDao, StoreProductLog> implements StoreProductLogService {

    @Resource
    private StoreProductLogDao dao;

}

