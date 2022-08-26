package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 19:57
 * @desc 登录信息
 */
@Data
@ApiModel(value = "登录实体")
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
}
