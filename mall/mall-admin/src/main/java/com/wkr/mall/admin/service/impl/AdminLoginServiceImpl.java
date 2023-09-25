package com.wkr.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.wkr.mall.admin.service.AdminLoginService;
import com.wkr.mall.admin.service.ValidateCodeService;
import com.wkr.admin.common.constants.SysConfigConstants;
import com.wkr.admin.common.constants.SysGroupDataConstants;
import com.wkr.admin.common.exception.MallException;
import com.wkr.admin.common.model.system.SystemAdmin;
import com.wkr.admin.common.model.system.SystemMenu;
import com.wkr.admin.common.model.system.SystemPermissions;
import com.wkr.admin.common.request.SystemAdminLoginRequest;
import com.wkr.admin.common.response.MenusResponse;
import com.wkr.admin.common.response.SystemAdminResponse;
import com.wkr.admin.common.response.SystemGroupDataAdminLoginBannerResponse;
import com.wkr.admin.common.response.SystemLoginResponse;
import com.wkr.admin.common.utils.SecurityUtil;
import com.wkr.admin.common.vo.LoginUserVo;
import com.wkr.admin.common.vo.MenuTree;
import com.wkr.mall.service.service.SystemAdminService;
import com.wkr.mall.service.service.SystemConfigService;
import com.wkr.mall.service.service.SystemGroupDataService;
import com.wkr.mall.service.service.SystemMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 管理端登录服务实现类
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    private Logger logger = LogManager.getLogger(AdminLoginServiceImpl.class);

    @Resource
    private TokenComponent tokenComponent;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SystemAdminService systemAdminService;
    @Resource
    private ValidateCodeService validateCodeService;
    @Resource
    private SystemConfigService systemConfigService;
    @Resource
    private SystemGroupDataService systemGroupDataService;
    @Resource
    private SystemMenuService systemMenuService;

    /**
     * PC登录
     */
    @Override
    public SystemLoginResponse login(SystemAdminLoginRequest loginRequest, String ip) {
        // 判断验证码
        boolean codeCheckResult = validateCodeService.check(loginRequest.getKey(), loginRequest.getCode());
        if (!codeCheckResult) {
            logger.error("验证码验证失败, account={}", loginRequest.getAccount());
            throw new MallException("验证码不正确");
        }
        // 用户验证
        Authentication authentication;
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getAccount(), loginRequest.getPwd()));
        } catch (AuthenticationException e) {
            if (e instanceof BadCredentialsException) {
                throw new MallException("用户不存在或密码错误");
            }
            throw new MallException(e.getMessage());
        }
        LoginUserVo loginUser = (LoginUserVo) authentication.getPrincipal();
        SystemAdmin systemAdmin = loginUser.getUser();

        String token = tokenComponent.createToken(loginUser);
        SystemLoginResponse systemAdminResponse = new SystemLoginResponse();
        systemAdminResponse.setToken(token);
        BeanUtils.copyProperties(systemAdmin, systemAdminResponse);

        //更新最后登录信息
        systemAdmin.setLoginCount(systemAdmin.getLoginCount() + 1);
        systemAdmin.setLastIp(ip);
        systemAdminService.updateById(systemAdmin);
        return systemAdminResponse;
    }

    /**
     * 用户登出
     */
    @Override
    public Boolean logout() {
        LoginUserVo loginUserVo = SecurityUtil.getLoginUserVo();
        if (ObjectUtil.isNotNull(loginUserVo)) {
            // 删除用户缓存记录
            tokenComponent.delLoginUser(loginUserVo.getToken());
        }
        return true;
    }

    /**
     * 获取登录页图片
     * @return Map
     */
    @Override
    public Map<String, Object> getLoginPic() {
        Map<String, Object> map = new HashMap<>();
        //背景图
        map.put("backgroundImage", systemConfigService.getValueByKey(SysConfigConstants.CONFIG_KEY_ADMIN_LOGIN_BACKGROUND_IMAGE));
        //logo
        map.put("logo", systemConfigService.getValueByKey(SysConfigConstants.CONFIG_KEY_ADMIN_LOGIN_LOGO_LEFT_TOP));
        map.put("loginLogo", systemConfigService.getValueByKey(SysConfigConstants.CONFIG_KEY_ADMIN_LOGIN_LOGO_LOGIN));
        //轮播图
        List<SystemGroupDataAdminLoginBannerResponse> bannerList = systemGroupDataService.getListByGid(SysGroupDataConstants.GROUP_DATA_ID_ADMIN_LOGIN_BANNER_IMAGE_LIST, SystemGroupDataAdminLoginBannerResponse.class);
        map.put("banner", bannerList);
        return map;
    }

    /**
     * 获取管理员可访问目录
     * @return List<MenusResponse>
     */
    @Override
    public List<MenusResponse> getMenus() {
        LoginUserVo loginUserVo = SecurityUtil.getLoginUserVo();
        List<String> roleList = Stream.of(loginUserVo.getUser().getRoles().split(",")).collect(Collectors.toList());
        List<SystemMenu> menuList;
        if (roleList.contains("1")) {// 超管
            menuList = systemMenuService.findAllCatalogue();
        } else {
            menuList = systemMenuService.getMenusByUserId(loginUserVo.getUser().getId());
        }
        // 组装前端对象
        List<MenusResponse> responseList = menuList.stream().map(e -> {
            MenusResponse response = new MenusResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        }).collect(Collectors.toList());

        MenuTree menuTree = new MenuTree(responseList);
        return menuTree.buildTree();
    }

    /**
     * 根据Token获取对应用户信息
     */
    @Override
    public SystemAdminResponse getInfoByToken() {
        LoginUserVo loginUserVo = SecurityUtil.getLoginUserVo();
        SystemAdmin systemAdmin = loginUserVo.getUser();
        SystemAdminResponse systemAdminResponse = new SystemAdminResponse();
        BeanUtils.copyProperties(systemAdmin, systemAdminResponse);
        List<String> roleList = Stream.of(systemAdmin.getRoles().split(",")).collect(Collectors.toList());
        List<String> permList = CollUtil.newArrayList();
        if (roleList.contains("1")) {
            permList.add("*:*:*");
        } else {
            permList = loginUserVo.getPermissions().stream().map(SystemPermissions::getPath).collect(Collectors.toList());
        }
        systemAdminResponse.setPermissionsList(permList);
        return systemAdminResponse;
    }
}
