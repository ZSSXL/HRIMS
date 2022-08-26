package com.zss.hrims.service;

import com.zss.hrims.model.dto.EnterprisePostDTO;

/**
 * @author ZSS
 * @date 2022/7/25 23:07
 * @desc 企业岗位服务层接口
 */
public interface EnterprisePostService {

    /**
     * 添加企业岗位的关系
     *
     * @param enterprisePostDTO 企业岗位关系信息
     * @return 是否创建成功
     */
    Boolean create(EnterprisePostDTO enterprisePostDTO);

    /**
     * 从该企业中移除指定岗位
     *
     * @param entId  企业Id
     * @param postId 岗位Id
     * @return 是否成功移除
     */
    Boolean removeEntPost(String entId, String postId);
}
