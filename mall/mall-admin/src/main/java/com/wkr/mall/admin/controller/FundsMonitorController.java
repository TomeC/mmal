package com.wkr.mall.admin.controller;

import com.wkr.admin.common.page.CommonPage;
import com.wkr.admin.common.response.CommonResult;
import com.wkr.admin.common.request.PageParamRequest;
import com.wkr.admin.common.request.BrokerageRecordRequest;
import com.wkr.admin.common.request.FundsMonitorRequest;
import com.wkr.admin.common.response.MonitorResponse;
import com.wkr.admin.common.model.user.UserBrokerageRecord;
import com.wkr.mall.service.service.UserBillService;
import com.wkr.mall.service.service.UserFundsMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户提现表 前端控制器

 */
@Slf4j
@RestController
@RequestMapping("api/admin/finance/founds/monitor")
@Api(tags = "财务 -- 资金监控")
public class FundsMonitorController {

    @Autowired
    private UserBillService userBillService;

    @Autowired
    private UserFundsMonitorService userFundsMonitorService;

    /**
     * 分页显示资金监控
     * @param request 搜索条件
     * @param pageParamRequest 分页参数
     */
    @PreAuthorize("hasAuthority('admin:finance:monitor:list')")
    @ApiOperation(value = "资金监控")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<MonitorResponse>>  getList(@Validated FundsMonitorRequest request, @Validated PageParamRequest pageParamRequest){
        CommonPage<MonitorResponse> userExtractCommonPage = CommonPage.restPage(userBillService.fundMonitoring(request, pageParamRequest));
        return CommonResult.success(userExtractCommonPage);
    }

    /**
     * 佣金记录
     * @param request 搜索条件
     * @param pageParamRequest 分页参数
     */
    @PreAuthorize("hasAuthority('admin:finance:monitor:brokerage:record')")
    @ApiOperation(value = "佣金记录")
    @RequestMapping(value = "/brokerage/record", method = RequestMethod.GET)
    public CommonResult<CommonPage<UserBrokerageRecord>> brokerageRecord(@Validated BrokerageRecordRequest request, @Validated PageParamRequest pageParamRequest){
        return CommonResult.success(CommonPage.restPage(userFundsMonitorService.getBrokerageRecord(request, pageParamRequest)));
    }
}



