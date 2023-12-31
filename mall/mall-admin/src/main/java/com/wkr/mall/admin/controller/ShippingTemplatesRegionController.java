package com.wkr.mall.admin.controller;

import com.wkr.admin.common.request.ShippingTemplatesRegionRequest;
import com.wkr.admin.common.response.CommonResult;
import com.wkr.mall.service.service.ShippingTemplatesRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  物流付费前端控制器
 *   +----------------------------------------------------------------------
 *  | mall [ mall赋能开发者，助力企业发展 ]
 *  +----------------------------------------------------------------------
 *  | Copyright (c) 2016~2022 https://www.mall.com All rights reserved.
 *  +----------------------------------------------------------------------
 *  | Licensed mall并不是自由软件，未经许可不能去掉mall相关版权
 *  +----------------------------------------------------------------------
 *  | Author: mall Team <admin@mall.com>
 *  +----------------------------------------------------------------------
 */
@Slf4j
@RestController
@RequestMapping("api/admin/express/shipping/region")
@Api(tags = "设置 -- 物流 -- 付费")
public class ShippingTemplatesRegionController {

    @Autowired
    private ShippingTemplatesRegionService shippingTemplatesRegionService;

    /**
     * 根据模板id查询数据
     * @param tempId Integer 模板id
     */
    @PreAuthorize("hasAuthority('admin:shipping:templates:region:list')")
    @ApiOperation(value = "根据模板id查询数据")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<ShippingTemplatesRegionRequest>> getList(@RequestParam Integer tempId){
        return CommonResult.success(shippingTemplatesRegionService.getListGroup(tempId));
    }
}
