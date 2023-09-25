package com.wkr.mall.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wkr.admin.common.model.order.StoreOrder;
import com.wkr.admin.common.request.StoreOrderRefundRequest;


/**
 * StoreOrderRefundService 接口

 */
public interface StoreOrderRefundService extends IService<StoreOrder> {

    void refund(StoreOrderRefundRequest request, StoreOrder storeOrder);
}
