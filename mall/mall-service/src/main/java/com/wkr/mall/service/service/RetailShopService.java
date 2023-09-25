package com.wkr.mall.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wkr.admin.common.page.CommonPage;
import com.wkr.admin.common.request.PageParamRequest;
import com.wkr.admin.common.request.RetailShopRequest;
import com.wkr.admin.common.response.SpreadUserResponse;
import com.wkr.admin.common.model.user.User;

/**
 * 分销业务

 */
public interface RetailShopService extends IService<User> {

    /**
     * 分销员列表
     * @param keywords 搜索参数
     * @param dateLimit 时间参数
     * @param pageRequest 分页参数
     * @return CommonPage
     */
    CommonPage<SpreadUserResponse> getSpreadPeopleList(String keywords, String dateLimit, PageParamRequest pageRequest);

    /**
     * 获取分销配置
     * @return 分销配置信息
     */
    RetailShopRequest getManageInfo();

    /**
     * 保存或者更新分销配置信息
     * @param retailShopRequest 待保存数据
     * @return 保存结果
     */
    boolean setManageInfo(RetailShopRequest retailShopRequest);

}
