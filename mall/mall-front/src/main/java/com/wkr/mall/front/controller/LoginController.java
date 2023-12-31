package com.wkr.mall.front.controller;


import com.wkr.admin.common.request.LoginMobileRequest;
import com.wkr.admin.common.request.LoginRequest;
import com.wkr.admin.common.response.CommonResult;
import com.wkr.admin.common.response.LoginResponse;
import com.wkr.mall.front.service.LoginService;
import com.wkr.mall.service.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登陆 前端控制器

 */
@Slf4j
@RestController("FrontLoginController")
@RequestMapping("api/front")
@Api(tags = "用户 -- 登录注册")
public class LoginController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private LoginService loginService;

    /**
     * 手机号登录接口
     */
    @ApiOperation(value = "手机号登录接口")
    @RequestMapping(value = "/login/mobile", method = RequestMethod.POST)
    public CommonResult<LoginResponse> phoneLogin(@RequestBody @Validated LoginMobileRequest loginRequest) {
        return CommonResult.success(loginService.phoneLogin(loginRequest));
    }

    /**
     * 账号密码登录
     */
    @ApiOperation(value = "账号密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        return CommonResult.success(loginService.login(loginRequest));
    }


    /**
     * 退出登录
     */
    @ApiOperation(value = "退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public CommonResult<String> loginOut(HttpServletRequest request){
        loginService.loginOut(request);
        return CommonResult.success();
    }

    /**
     * 发送短信登录验证码
     * @param phone 手机号码
     * @return 发送是否成功
     */
    @ApiOperation(value = "发送短信登录验证码")
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name="phone", value="手机号码", required = true)
    })
    public CommonResult<Object> sendCode(@RequestParam String phone){
        if(smsService.sendCommonCode(phone)){
            return CommonResult.success("发送成功");
        }else{
            return CommonResult.failed("发送失败");
        }
    }
}



