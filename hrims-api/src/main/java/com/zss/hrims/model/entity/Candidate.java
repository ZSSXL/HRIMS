package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 22:38
 * @desc 候选人实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_candidate")
public class Candidate extends Model<Candidate> implements Serializable {

    /**
     * 候选人id
     */
    private String id;

    /**
     * 候选人名称
     */
    private String name;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 电话
     */
    private String phone;

    /**
     * 面试岗位
     */
    private String postId;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 毕业学校
     */
    private String graduationSchool;

    /**
     * 学历
     */
    private String education;

    /**
     * 专业
     */
    private String specialized;

    /**
     * 招生模式 -- 全日制/成人教育/假学历
     */
    private String enrollmentModel;

    /**
     * 年限
     */
    private String years;

    /**
     * 技能
     */
    private String skill;

    /**
     * 学历验证
     */
    private Boolean educationalVerify;

    /**
     * 结论
     */
    private String conclusion;

    /**
     * 评级 - 初/中/高
     */
    private Integer rating;

    /**
     * 入职日期
     */
    private String entryDate;

    /**
     * 简历来源
     *
     * @see com.zss.hrims.model.entity.ResumeSource
     */
    private String resumeSource;

    /**
     * 候选人状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;
}
