package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 13:41
 * @desc 工作岗位添加
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "工作岗位添加")
public class PostDTO implements Serializable {

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "岗位名称")
    private String describe;

}
