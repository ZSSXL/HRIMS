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
 * @date 2022/8/24 13:23
 * @desc 图片预览信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "图片预览信息")
public class ImagePreviewVO implements Serializable {

    @ApiModelProperty(value = "附件Id")
    private String id;

    @ApiModelProperty(value = "预览链接")
    private String url;
}
