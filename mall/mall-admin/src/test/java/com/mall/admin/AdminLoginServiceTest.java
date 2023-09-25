package com.mall.admin;

import com.wkr.admin.common.request.SystemAdminLoginRequest;
import com.wkr.admin.common.response.SystemLoginResponse;
import com.wkr.admin.common.utils.RedisUtil;
import com.wkr.mall.admin.MallAdminApplication;
import com.wkr.mall.admin.service.AdminLoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 王锟
 * @Description: 基础测试类
 * @date 2023/8/29 9:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallAdminApplication.class)
public class AdminLoginServiceTest {
    private static final Logger logger = LogManager.getLogger(AdminLoginServiceTest.class);
    @Resource
    private AdminLoginService adminLoginService;
    @Resource
    private RedisUtil redisUtil;

    @Test
    public void loginTest() {
        // 设置验证码
        redisUtil.set("validate_code_key_admin", "123456");

        SystemAdminLoginRequest loginRequest = new SystemAdminLoginRequest();
        loginRequest.setAccount("admin");
        loginRequest.setPwd("123456");
        loginRequest.setKey("key_admin");
        loginRequest.setCode("123456");

        SystemLoginResponse response = adminLoginService.login(loginRequest, "127.0.0.1");
        logger.info("login response={}", response);
        Assert.assertNotNull(response);
    }

    @Test
    public void logoutTest() {
        Boolean response = adminLoginService.logout();
        logger.info("logout result={}", response);
        Assert.assertTrue(response);
    }
}
