package com.zss.hrims.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/28 11:14
 * @desc 企业所属岗位信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntPostBO implements Serializable {

    /**
     * 岗位Id
     */
    private String postId;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 年限要求
     */
    private String ageLimit;

    /**
     * 企业对该岗位的需求描述
     */
    private String demand;
}
