package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/8/25 13:29
 * @desc 未绑定企业查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "未绑定企业查询")
public class BindEntQuery implements Serializable {

    @ApiModelProperty(value = "用户Id")
    private String userId;
}
