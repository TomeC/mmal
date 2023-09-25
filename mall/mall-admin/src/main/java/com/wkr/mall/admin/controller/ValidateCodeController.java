package com.wkr.mall.admin.controller;

import com.wkr.admin.common.response.CommonResult;
import com.wkr.mall.admin.service.ValidateCodeService;
import com.wkr.mall.admin.vo.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 验证码服务
 * @author : wkr
 */
@Slf4j
@RestController
@RequestMapping("api/admin/validate/code")
@Api(tags = "验证码服务")
public class ValidateCodeController {

    @Resource
    private ValidateCodeService validateCodeService;

    /**
     * 获取图片验证码
     * @return CommonResult
     */
    @ApiOperation(value="获取验证码")
    @GetMapping(value = "/get")
    public CommonResult<ValidateCode> get() {
        ValidateCode validateCode = validateCodeService.get();
        return CommonResult.success(validateCode);
    }
}



