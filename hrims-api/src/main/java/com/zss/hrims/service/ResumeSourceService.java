package com.zss.hrims.service;

import com.zss.hrims.model.dto.ResumeSourceDTO;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:50
 * @desc 简历来源服务层接口
 */
public interface ResumeSourceService {

    /**
     * 添加建立来源
     *
     * @param resumeSourceDTO 建立来源
     * @return 是否添加成功
     */
    Boolean create(ResumeSourceDTO resumeSourceDTO);

    /**
     * 获取简历列表
     *
     * @return 简历来源列表
     */
    List<String> list();
}
