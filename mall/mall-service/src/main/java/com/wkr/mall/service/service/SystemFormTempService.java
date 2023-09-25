package com.wkr.mall.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wkr.admin.common.request.PageParamRequest;
import com.wkr.admin.common.model.system.SystemFormTemp;
import com.wkr.admin.common.request.SystemFormCheckRequest;
import com.wkr.admin.common.request.SystemFormTempRequest;
import com.wkr.admin.common.request.SystemFormTempSearchRequest;

import java.util.List;

/**
 * SystemFormTempService 接口

 */
public interface SystemFormTempService extends IService<SystemFormTemp> {

    /**
     * 列表
     * @param request 请求参数
     * @param pageParamRequest 分页类参数
     * @return List<SystemFormTemp>
     */
    List<SystemFormTemp> getList(SystemFormTempSearchRequest request, PageParamRequest pageParamRequest);

    /**
     * 验证item规则
     * @param systemFormCheckRequest SystemFormCheckRequest 表单数据提交
     */
    void checkForm(SystemFormCheckRequest systemFormCheckRequest);

    /**
     * 新增表单模板
     * @param systemFormTempRequest 新增参数
     */
    Boolean add(SystemFormTempRequest systemFormTempRequest);

    /**
     * 修改表单模板
     * @param id integer id
     * @param systemFormTempRequest 修改参数
     */
    Boolean edit(Integer id, SystemFormTempRequest systemFormTempRequest);
}
