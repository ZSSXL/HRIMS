package com.zss.hrims.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/26 16:48
 * @desc 候选人详细信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateDetailVO implements Serializable {

    @ApiModelProperty(value = "候选人Id")
    private String id;

    @ApiModelProperty(value = "候选人名称")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "面试岗位")
    private String post;

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

    @ApiModelProperty(value = "评级 -- 初中高")
    private String rating;

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

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "企业相关")
    private EnterpriseBaseVO enterprise;

    @ApiModelProperty(value = "面试过程")
    private List<InterviewVO> interviewList;

    @ApiModelProperty(value = "图片文件地址")
    private List<ImagePreviewVO> imageUrls;
}
