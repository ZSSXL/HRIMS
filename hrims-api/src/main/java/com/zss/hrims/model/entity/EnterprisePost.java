package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 23:08
 * @desc 企业岗位实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_enterprise_post")
public class EnterprisePost extends Model<EnterprisePost> implements Serializable {

    private String id;

    /**
     * 企业Id
     */
    private String enterpriseId;

    /**
     * 岗位Id
     */
    private String postId;

    /**
     * 年限要求
     */
    private String ageLimit;

    /**
     * 企业对该岗位的需求描述
     */
    private String demand;

    /**
     * 创建时间
     */
    private String createTime;
}
