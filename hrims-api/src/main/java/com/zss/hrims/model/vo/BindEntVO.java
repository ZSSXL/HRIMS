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
 * @date 2022/8/25 13:31
 * @desc 未绑定企业
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "未绑定企业")
public class BindEntVO implements Serializable {

    @ApiModelProperty(value = "企业Id")
    private String entId;

    @ApiModelProperty(value = "企业名称")
    private String entName;

    @ApiModelProperty(value = "是否绑定")
    private Boolean bind;
}
