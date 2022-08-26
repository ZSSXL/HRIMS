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
 * @date 2022/7/23 20:03
 * @desc 用户信息实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户信息")
public class UserVO implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "用户角色")
    private Integer role;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
