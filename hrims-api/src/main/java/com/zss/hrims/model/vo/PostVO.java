package com.zss.hrims.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 23:18
 * @desc 岗位前端展示实体
 */
@Data
@Builder
@ApiModel(value = "岗位前端展示")
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {

    @ApiModelProperty(value = "岗位Id")
    private String id;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "岗位描述")
    private String describe;
}
