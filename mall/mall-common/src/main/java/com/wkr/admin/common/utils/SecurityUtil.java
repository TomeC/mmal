package com.wkr.admin.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.wkr.admin.common.exception.MallException;
import com.wkr.admin.common.vo.LoginUserVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * security工具类

 */
public class SecurityUtil {

    /**
     * 获取管理员信息（从security中）
     */
    public static LoginUserVo getLoginUserVo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserVo loginUserVo = (LoginUserVo) authentication.getPrincipal();
        if (ObjectUtil.isNull(loginUserVo)) {
            throw new MallException("登录信息已过期，请重新登录");
        }
        return loginUserVo;
    }

}
