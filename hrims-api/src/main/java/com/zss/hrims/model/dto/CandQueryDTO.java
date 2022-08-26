package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/29 16:45
 * @desc 候选人搜索
 */
@Data
@ApiModel(value = "候选人搜索")
public class CandQueryDTO implements Serializable {

    @ApiModelProperty(value = "候选人名称 -- 模糊查询")
    private String name;

    @ApiModelProperty(value = "评级")
    private String rating;

    @ApiModelProperty(value = "企业Id")
    private String entId;

    @ApiModelProperty(value = "是否工作")
    private Boolean work;

    @ApiModelProperty(value = "时间查询 -- 日周月年")
    private String timeQuery;

    @ApiModelProperty(value = "当前页，从0开始")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页大小，默认20")
    private Integer pageSize;
}
