package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/24 23:18
 * @desc 简历来源实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_resume_source")
public class ResumeSource extends Model<ResumeSource> implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 简历来源
     */
    private String source;

    /**
     * 来源别名
     */
    private String alias;

    /**
     * 创建时间
     */
    private String createTime;
}
