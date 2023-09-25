package com.wkr.mall.service.dao;

import com.wkr.admin.common.model.express.ShippingTemplatesRegion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wkr.admin.common.request.ShippingTemplatesRegionRequest;

import java.util.List;

/**
 *  Mapper 接口

 */
public interface ShippingTemplatesRegionDao extends BaseMapper<ShippingTemplatesRegion> {

    List<ShippingTemplatesRegionRequest> getListGroup(Integer tempId);
}
