package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.entity.Interview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2022/7/23 23:50
 * @desc 面试信息持久化
 */
@Mapper
@Repository
public interface InterviewMapper extends BaseMapper<Interview> {

    /**
     * 获取最新的面试状态
     *
     * @param candidateId 候选人Id
     * @return 面试状态
     */
    String selectNewestState(String candidateId);
}
