package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/8/24 11:08
 * @desc 附件实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "hri_attachment")
public class Attachment extends Model<Candidate> implements Serializable {

    /**
     * 附件Id
     */
    private String id;

    /**
     * 归属
     */
    private String key;

    /**
     * 文件全路径
     */
    private String fullPath;

    /**
     * 创建时间
     */
    private String createTime;
}
