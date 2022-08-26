package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 21:35
 * @desc 用户注册实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户注册实体")
public class RegisterDTO implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
