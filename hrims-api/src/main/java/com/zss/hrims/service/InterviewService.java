package com.zss.hrims.service;

import com.zss.hrims.model.dto.InterviewDTO;
import com.zss.hrims.model.dto.UpdateInterviewDTO;
import com.zss.hrims.model.vo.InterviewVO;

/**
 * @author ZSS
 * @date 2022/7/26 10:40
 * @desc 面试服务层接口
 */
public interface InterviewService {

    /**
     * 添加面试
     *
     * @param interviewDTO 面试基本信息
     * @return 是否创建成功
     */
    Boolean create(InterviewDTO interviewDTO);

    /**
     * 判断之前简历的最新状态，只有最新的面试状态是成功，
     * 才会创建下一步
     *
     * @param candidateId 候选人Id
     * @return 能否继续
     */
    Boolean state(String candidateId);

    /**
     * 更新面试信息
     *
     * @param updateDTO 面试信息
     * @return 最新结果
     */
    InterviewVO update(UpdateInterviewDTO updateDTO);
}
