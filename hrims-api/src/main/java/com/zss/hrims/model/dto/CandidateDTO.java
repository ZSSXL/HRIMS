package com.zss.hrims.model.dto;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/25 17:18
 * @desc 候选人添加
 */
@Data
@ApiModel(value = "添加候选人")
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {

    @NotNull
    @ApiModelProperty(value = "候选人名称")
    private String name;

    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "电话")
    @NotNull
    private String phone;

    @ApiModelProperty(value = "目标企业公司")
    private String entId;

    @ApiModelProperty(value = "面试岗位")
    private String postId;

    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    @ApiModelProperty(value = "毕业学校")
    private String graduationSchool;

    @ApiModelProperty(value = "学历")
    @NotNull
    private String education;

    @ApiModelProperty(value = "专业")
    @NotNull
    private String specialized;

    @ApiModelProperty(value = "招生模式")
    private String enrollmentModel;

    @ApiModelProperty(value = "年限")
    private String years;

    @ApiModelProperty(value = "技能")
    private String skill;

    @ApiModelProperty(value = "评级 - 初/中/高")
    private String rating;

    @ApiModelProperty(value = "学历验证")
    private Boolean educationalVerify;

    @NotNull
    @ApiModelProperty(value = "简历来源")
    private String resumeSource;

    @ApiModelProperty(value = "备注")
    private String remark;
}
