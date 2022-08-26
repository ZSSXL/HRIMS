package com.zss.hrims.service;

import com.zss.hrims.model.dto.CandQueryDTO;
import com.zss.hrims.model.dto.CandidateDTO;
import com.zss.hrims.model.dto.UpdateCandidateDTO;
import com.zss.hrims.model.vo.CandidateDetailVO;
import com.zss.hrims.model.vo.CandidateRecordVO;

/**
 * @author ZSS
 * @date 2022/7/25 17:14
 * @desc 候选人服务层接口
 */
public interface CandidateService {

    /**
     * 添加候选人
     *
     * @param candidateDTO 候选人基本信息
     * @return 是否添加成功
     */
    Boolean create(CandidateDTO candidateDTO);

    /**
     * 获取候选人详情
     *
     * @param candId 候选人Id
     * @return 详情
     */
    CandidateDetailVO detail(String candId);

    /**
     * 更新候选人基本信息
     *
     * @param updateCandidateDTO 更新信息
     * @return 最新信息
     */
    CandidateDetailVO update(UpdateCandidateDTO updateCandidateDTO);

    /**
     * 通过身份证号判断是否存在
     *
     * @param idNumber 身份证号
     * @return 是否已经创建
     */
    Boolean exist(String idNumber);

    /**
     * 查询候选人
     *
     * @param candQueryDTO 查询条件
     * @return 查询结果
     */
    CandidateRecordVO query(CandQueryDTO candQueryDTO);
}
