package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/23 23:04
 * @desc 工作岗位实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_post")
public class Post extends Model<Post> implements Serializable {

    private String id;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位描述
     */
    private String describe;

    /**
     * 创建时间
     */
    private String createTime;
}
