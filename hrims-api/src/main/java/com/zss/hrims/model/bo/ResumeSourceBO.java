package com.zss.hrims.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/26 16:21
 * @desc 简历来源
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeSourceBO implements Serializable {

    /**
     * 简历来源
     */
    private String source;

    /**
     * 别名
     */
    private String alias;
}
