package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 22:35
 * @desc 企业实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_enterprise")
public class Enterprise extends Model<Enterprise> implements Serializable {

    private String id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 是否在合作
     */
    private Boolean cooperation;

    /**
     * 创建时间
     */
    private String createTime;
}
