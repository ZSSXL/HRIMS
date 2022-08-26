package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.InterviewMapper;
import com.zss.hrims.model.dto.InterviewDTO;
import com.zss.hrims.model.dto.UpdateInterviewDTO;
import com.zss.hrims.model.entity.Interview;
import com.zss.hrims.model.vo.InterviewVO;
import com.zss.hrims.service.InterviewService;
import com.zss.hrims.utils.CommonUtil;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2022/7/26 10:41
 * @desc 面试服务层接口方法实现
 */
@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewMapper interviewMapper;

    @Autowired
    public InterviewServiceImpl(InterviewMapper interviewMapper) {
        this.interviewMapper = interviewMapper;
    }

    @Override
    public Boolean create(InterviewDTO interviewDTO) {
        QueryWrapper<Interview> query = new QueryWrapper<>();
        query.eq("candidate_id", interviewDTO.getCandidateId());
        Integer count = interviewMapper.selectCount(query);

        Interview interview = Interview.builder()
                .id(UUIDUtil.getId())
                .candidateId(interviewDTO.getCandidateId())
                .rounds(count == 0 ? 1 : ++count)
                .state(StringUtils.isEmpty(interviewDTO.getState()) ?
                        HrimsConstants.InterviewStateEnum.RECOMMEND.getCode() :
                        HrimsConstants.InterviewStateEnum.getCodeByDesc(interviewDTO.getState()))
                .interviewTime(interviewDTO.getInterviewTime())
                .createTime(DateUtil.currentTimestamp())
                .build();
        return interviewMapper.insert(interview) == 1;
    }

    @Override
    public Boolean state(String candidateId) {
        String state = interviewMapper.selectNewestState(candidateId);
        if (StringUtils.isEmpty(state)) {
            return true;
        } else {
            return StringUtils.equals(state, HrimsConstants.InterviewStateEnum.SUCCESS.getDesc());
        }
    }

    @Override
    public InterviewVO update(UpdateInterviewDTO updateDTO) {
        Interview old = interviewMapper.selectById(updateDTO.getId());
        if (old == null) {
            return null;
        }
        UpdateWrapper<Interview> updateQuery = new UpdateWrapper<>();
        updateQuery.eq("id", updateDTO.getId())
                .set("state", StringUtils.isEmpty(updateDTO.getState()) ?
                        HrimsConstants.InterviewStateEnum.RECOMMEND.getDesc() : updateDTO.getState())
                .set("hr_interview_advice", updateDTO.getHrInterviewAdvice());
        if (StringUtils.isNotEmpty(updateDTO.getInterviewTime())) {
            updateQuery.set("interview_time", updateDTO.getInterviewTime());
        }
        int update = interviewMapper.update(null, updateQuery);
        if (update == 1) {
            Interview newest = interviewMapper.selectById(updateDTO.getId());
            return InterviewVO.builder()
                    .id(newest.getId())
                    .candidateId(newest.getCandidateId())
                    .interviewTime(DateUtil.timestampToDateComplete(newest.getInterviewTime()))
                    .hrInterviewAdvice(newest.getHrInterviewAdvice())
                    .rounds(CommonUtil.roundParse(newest.getRounds()))
                    .state(HrimsConstants.InterviewStateEnum.getDescByCode(newest.getState()))
                    .createTime(DateUtil.timestampToDateComplete(newest.getCreateTime()))
                    .build();
        } else {
            return null;
        }
    }
}
