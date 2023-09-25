package com.wkr.mall.service.dao;

import com.wkr.admin.common.model.express.ShippingTemplatesFree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wkr.admin.common.request.ShippingTemplatesFreeRequest;

import java.util.List;

/**
 *  Mapper 接口

 */
public interface ShippingTemplatesFreeDao extends BaseMapper<ShippingTemplatesFree> {

    List<ShippingTemplatesFreeRequest> getListGroup(Integer tempId);
}
