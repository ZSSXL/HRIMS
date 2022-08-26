package com.zss.hrims.service.impl;

import com.zss.hrims.mapper.ResumeSourceMapper;
import com.zss.hrims.model.bo.ResumeSourceBO;
import com.zss.hrims.model.dto.ResumeSourceDTO;
import com.zss.hrims.model.entity.ResumeSource;
import com.zss.hrims.service.ResumeSourceService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:50
 * @desc 建立来源服务层接口方法实现
 */
@Service
public class ResumeSourceServiceImpl implements ResumeSourceService {

    private final ResumeSourceMapper resumeSourceMapper;

    @Autowired
    public ResumeSourceServiceImpl(ResumeSourceMapper resumeSourceMapper) {
        this.resumeSourceMapper = resumeSourceMapper;
    }

    @Override
    public Boolean create(ResumeSourceDTO resumeSourceDTO) {
        ResumeSource resumeSource = ResumeSource.builder()
                .id(UUIDUtil.getId())
                .source(resumeSourceDTO.getSource())
                .alias(resumeSourceDTO.getAlias())
                .createTime(DateUtil.currentTimestamp())
                .build();
        return resumeSourceMapper.insert(resumeSource) == 1;
    }

    @Override
    public List<String> list() {
        List<ResumeSourceBO> resumeSourceList = resumeSourceMapper.selectResumeSource();
        List<String> result = new ArrayList<>();
        for (ResumeSourceBO bo : resumeSourceList) {
            if (StringUtils.isEmpty(bo.getAlias())){
                result.add(bo.getSource());
            } else {
                result.add(bo.getAlias());
            }
        }
        return result;
    }
}
