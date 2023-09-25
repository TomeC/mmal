package com.wkr.mall.admin.manager;

import cn.hutool.core.util.ObjectUtil;
import com.wkr.admin.common.exception.MallException;
import com.wkr.admin.common.utils.MallUtil;
import com.wkr.admin.common.vo.LoginUserVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义验证（admin登录）
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        //以下自定义方法，判断是否登录成功
        LoginUserVo userDetails = (LoginUserVo) userDetailsService.loadUserByUsername(name);
        if (ObjectUtil.isNull(userDetails)) {
            throw new MallException("用户名不存在");
        }
        // base64加密获取真正密码
        String encryptPassword = MallUtil.encryptPassword(password, name);
        if (!userDetails.getUser().getPwd().equals(encryptPassword)) {
            throw new MallException("账号或者密码不正确");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //确保authentication能转成该类
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
