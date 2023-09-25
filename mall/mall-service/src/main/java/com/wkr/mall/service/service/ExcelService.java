package com.wkr.mall.service.service;

import com.wkr.admin.common.request.StoreBargainSearchRequest;
import com.wkr.admin.common.request.StoreCombinationSearchRequest;
import com.wkr.admin.common.request.StoreOrderSearchRequest;
import com.wkr.admin.common.request.StoreProductSearchRequest;

/**
* StoreProductService 接口

*/
public interface ExcelService{

    /**
     * 导出砍价商品
     * @param request 请求参数
     * @return 导出地址
     */
    String exportBargainProduct(StoreBargainSearchRequest request);

    /**
     * 导出拼团商品
     * @param request 请求参数
     * @return 导出地址
     */
    String exportCombinationProduct(StoreCombinationSearchRequest request);

    /**
     * 商品导出
     * @param request 请求参数
     * @return 导出地址
     */
    String exportProduct(StoreProductSearchRequest request);

    /**
     * 订单导出
     * @param request 查询条件
     * @return 文件名称
     */
    String exportOrder(StoreOrderSearchRequest request);
}
