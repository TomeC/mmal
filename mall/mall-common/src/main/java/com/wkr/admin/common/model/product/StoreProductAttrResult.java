package com.wkr.admin.common.model.product;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品属性详情表

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_store_product_attr_result")
@ApiModel(value="StoreProductAttrResult对象", description="商品属性详情表")
public class StoreProductAttrResult implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "商品ID")
    private Integer productId;

    @ApiModelProperty(value = "商品属性参数")
    private String result;

    @ApiModelProperty(value = "上次修改时间")
    private Integer changeTime;

    @ApiModelProperty(value = "活动类型 0=商品，1=秒杀，2=砍价，3=拼团")
    private Integer type;


}