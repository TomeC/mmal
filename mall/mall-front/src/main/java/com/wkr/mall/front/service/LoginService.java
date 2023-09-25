package com.wkr.mall.front.service;

import com.wkr.admin.common.model.user.User;
import com.wkr.admin.common.request.LoginMobileRequest;
import com.wkr.admin.common.request.LoginRequest;
import com.wkr.admin.common.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 移动端登录服务类

 */
public interface LoginService {

    /**
     * 账号密码登录
     * @return LoginResponse
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * 手机号验证码登录
     */
    LoginResponse phoneLogin(LoginMobileRequest loginRequest);

    /**
     * 老绑定分销关系
     * @param user User 用户user类
     * @param spreadUid Integer 推广人id
     * @return Boolean
     */
    Boolean bindSpread(User user, Integer spreadUid);

    /**
     * 推出登录
     * @param request HttpServletRequest
     */
    void loginOut(HttpServletRequest request);
}
