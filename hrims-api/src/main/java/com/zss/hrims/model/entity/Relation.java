package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 22:58
 * @desc 关系实体
 * 包括如下关系：
 * 1. 用户 oneToMany 企业
 * 2. 企业 oneToMany 候选人
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_relation")
public class Relation extends Model<Relation> implements Serializable {

    private String id;

    /**
     * 单
     */
    private String key;

    /**
     * 多
     */
    private String value;
}
