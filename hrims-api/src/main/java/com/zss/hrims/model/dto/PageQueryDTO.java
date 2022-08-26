package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 10:45
 * @desc 分页查询数据
 */
@Data
@ApiModel(value = "分页查询")
public class PageQueryDTO implements Serializable {

    @ApiModelProperty(value = "匹配名称 -- 模糊查询")
    private String name;

    @ApiModelProperty(value = "当前页，从0开始")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页大小，默认20")
    private Integer pageSize;

    @ApiModelProperty(value = "true为倒序, false为倒序, default: 正序")
    private Boolean sort;
}
