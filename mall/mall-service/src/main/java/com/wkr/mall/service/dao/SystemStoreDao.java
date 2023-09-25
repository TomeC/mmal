package com.wkr.mall.service.dao;

import com.wkr.admin.common.request.StoreNearRequest;
import com.wkr.admin.common.vo.SystemStoreNearVo;
import com.wkr.admin.common.model.system.SystemStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 门店自提 Mapper 接口

 */
public interface SystemStoreDao extends BaseMapper<SystemStore> {

    List<SystemStoreNearVo> getNearList(StoreNearRequest request);
}

