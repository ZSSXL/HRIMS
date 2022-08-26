package com.zss.hrims.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/27 13:52
 * @desc 更新候选人信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "更新候选人信息")
public class UpdateCandidateDTO implements Serializable {

    @ApiModelProperty(value = "候选人Id")
    private String id;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    @ApiModelProperty(value = "毕业学校")
    private String graduationSchool;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "专业")
    private String specialized;

    @ApiModelProperty(value = "招生模式")
    private String enrollmentModel;

    @ApiModelProperty(value = "年限")
    private String years;

    @ApiModelProperty(value = "技能")
    private String skill;

    @ApiModelProperty(value = "学历验证")
    private Boolean educationalVerify;

    @ApiModelProperty(value = "结论")
    private String conclusion;

    @ApiModelProperty(value = "入职日期")
    private String entryDate;

    @ApiModelProperty(value = "简历来源")
    private String resumeSource;

    @ApiModelProperty(value = "候选人状态")
    private String state;

    @ApiModelProperty(value = "备注")
    private String remark;
}
