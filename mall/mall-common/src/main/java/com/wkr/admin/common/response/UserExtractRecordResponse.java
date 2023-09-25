package com.wkr.admin.common.response;

import com.wkr.admin.common.model.finance.UserExtract;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 提现记录响应对象
 * +----------------------------------------------------------------------
 *  | mall [ mall赋能开发者，助力企业发展 ]
 *  +----------------------------------------------------------------------
 *  | Copyright (c) 2016~2022 https://www.mall.com All rights reserved.
 *  +----------------------------------------------------------------------
 *  | Licensed mall并不是自由软件，未经许可不能去掉mall相关版权
 *  +----------------------------------------------------------------------
 *  | Author: mall Team <admin@mall.com>
 *  +----------------------------------------------------------------------
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserExtractRecordResponse对象", description="用户提现记录对象")
public class UserExtractRecordResponse {

    private static final long serialVersionUID=1L;

    public UserExtractRecordResponse() {}
    public UserExtractRecordResponse(String date, List<UserExtract> list) {
        this.date = date;
        this.list = list;
    }

    @ApiModelProperty(value = "月份")
    private String date;

    @ApiModelProperty(value = "数据")
    private List<UserExtract> list;
}
