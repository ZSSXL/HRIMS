package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.*;
import com.zss.hrims.model.bo.AttachmentBO;
import com.zss.hrims.model.bo.CandidateSimpleBO;
import com.zss.hrims.model.dto.CandQueryDTO;
import com.zss.hrims.model.dto.CandidateDTO;
import com.zss.hrims.model.dto.UpdateCandidateDTO;
import com.zss.hrims.model.entity.Candidate;
import com.zss.hrims.model.entity.Interview;
import com.zss.hrims.model.entity.Relation;
import com.zss.hrims.model.vo.*;
import com.zss.hrims.service.CandidateService;
import com.zss.hrims.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @date 2022/7/25 17:16
 * @desc
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateMapper candidateMapper;
    private final RelationMapper relationMapper;
    private final EnterpriseMapper enterpriseMapper;
    private final InterviewMapper interviewMapper;
    private final GeneralConverter converter;
    private final PostMapper postMapper;
    private final AttachmentMapper attachmentMapper;
    private final MinioUtil minioUtil;

    @Autowired
    public CandidateServiceImpl(CandidateMapper candidateMapper, RelationMapper relationMapper,
                                EnterpriseMapper enterpriseMapper, InterviewMapper interviewMapper,
                                GeneralConverter converter, PostMapper postMapper,
                                AttachmentMapper attachmentMapper, MinioUtil minioUtil) {
        this.candidateMapper = candidateMapper;
        this.relationMapper = relationMapper;
        this.enterpriseMapper = enterpriseMapper;
        this.interviewMapper = interviewMapper;
        this.converter = converter;
        this.postMapper = postMapper;
        this.attachmentMapper = attachmentMapper;
        this.minioUtil = minioUtil;
    }

    @Override
    public Boolean create(CandidateDTO candidateDTO) {
        Candidate candidate = converter.converter(candidateDTO, Candidate.class);
        String candidateId = UUIDUtil.getId();
        candidate.setId(candidateId);
        candidate.setEducationalVerify(true);
        candidate.setState(HrimsConstants.InterviewStateEnum.RECOMMEND.getCode());
        candidate.setCreateTime(DateUtil.currentTimestamp());

        if (HrimsConstants.RatingEnum.contains(candidateDTO.getRating())) {
            candidate.setRating(HrimsConstants.RatingEnum.getCode(candidateDTO.getRating()));
        } else {
            // 找不到则默认给初级
            candidate.setRating(HrimsConstants.RatingEnum.PRIMARY.getCode());
        }

        if (candidateMapper.insert(candidate) == 1) {
            // 绑定与企业的关系
            Relation relation = Relation.builder()
                    .id(UUIDUtil.getId())
                    .key(candidateDTO.getEntId())
                    .value(candidateId)
                    .build();
            return relationMapper.insert(relation) == 1;
        } else {
            return false;
        }
    }

    @Override
    public CandidateDetailVO detail(String candId) {
        // 获取候选人基本信息
        QueryWrapper<Candidate> candidateQuery = new QueryWrapper<>();
        candidateQuery.eq("id", candId);
        Candidate candidate = candidateMapper.selectOne(candidateQuery);
        if (candidate == null) {
            return null;
        }
        CandidateDetailVO candidateDetailVO = this.converter.converter(candidate, CandidateDetailVO.class);
        candidateDetailVO.setRating(HrimsConstants.RatingEnum.getLevel(candidate.getRating()));
        candidateDetailVO.setState(HrimsConstants.InterviewStateEnum.getDescByCode(candidate.getState()));
        candidateDetailVO.setCreateTime(DateUtil.timestampToDate(candidateDetailVO.getCreateTime()));
        candidateDetailVO.setPost(postMapper.selectNameById(candidate.getPostId()));

        // 获取企业名称
        String entId = relationMapper.selectKeyByValue(candId);
        String entName = enterpriseMapper.selectNameById(entId);
        EnterpriseBaseVO enterpriseBaseInfo = EnterpriseBaseVO.builder()
                .id(entId)
                .name(entName)
                .build();
        candidateDetailVO.setEnterprise(enterpriseBaseInfo);

        // 获取面试信息
        QueryWrapper<Interview> interviewQuery = new QueryWrapper<>();
        interviewQuery.eq("candidate_id", candId)
                .orderByAsc("create_time");
        List<Interview> interviewList = interviewMapper.selectList(interviewQuery);
        List<InterviewVO> interviewVOList = new ArrayList<>(interviewList.size());
        for (Interview interview : interviewList) {
            InterviewVO temp = InterviewVO.builder()
                    .id(interview.getId())
                    .candidateId(candId)
                    .interviewTime(DateUtil.timestampToDateComplete(interview.getInterviewTime()))
                    .hrInterviewAdvice(interview.getHrInterviewAdvice())
                    .rounds(CommonUtil.roundParse(interview.getRounds()))
                    .state(HrimsConstants.InterviewStateEnum.getDescByCode(interview.getState()))
                    .createTime(DateUtil.timestampToDateComplete(interview.getCreateTime()))
                    .build();
            interviewVOList.add(temp);
        }
        candidateDetailVO.setInterviewList(interviewVOList);

        // 获取图片预览地址
        List<AttachmentBO> attachmentBOList = attachmentMapper.selectByKey(candId);
        List<ImagePreviewVO> imagePreviewVOList = new ArrayList<>(attachmentBOList.size());
        attachmentBOList.forEach(attachmentBO -> {
            ImagePreviewVO build = ImagePreviewVO.builder()
                    .id(attachmentBO.getId())
                    .url(minioUtil.preview(attachmentBO.getFullPath()))
                    .build();
            imagePreviewVOList.add(build);
        });
        candidateDetailVO.setImageUrls(imagePreviewVOList);

        // 返回结果
        return candidateDetailVO;
    }

    @Override
    public CandidateDetailVO update(UpdateCandidateDTO updateCandidateDTO) {
        Candidate old = candidateMapper.selectById(updateCandidateDTO.getId());
        if (old == null) {
            return null;
        }
        Candidate updateObj = converter.converter(updateCandidateDTO, Candidate.class);
        updateObj.setName(old.getName());
        updateObj.setIdNumber(old.getId());
        updateObj.setCreateTime(DateUtil.currentTimestamp());
        updateObj.setState(HrimsConstants.InterviewStateEnum.getCodeByDesc(updateCandidateDTO.getState()));

        if (candidateMapper.updateById(updateObj) == 1) {
            return converter.converter(updateObj, CandidateDetailVO.class);
        }
        return null;
    }

    @Override
    public Boolean exist(String idNumber) {
        QueryWrapper<Candidate> query = new QueryWrapper<>();
        query.eq("id_number", idNumber);
        return candidateMapper.selectCount(query) == 1;
    }

    @Override
    public CandidateRecordVO query(CandQueryDTO candQueryDTO) {
        IPage<Candidate> candPage = PageUtil.of(candQueryDTO.getPageIndex(),
                candQueryDTO.getPageSize());
        String name;
        if (StringUtils.isEmpty(candQueryDTO.getName())) {
            name = "%%";
        } else {
            name = "%" + candQueryDTO.getName() + "%";
        }
        Integer rating = HrimsConstants.RatingEnum.getCode(candQueryDTO.getRating());
        String entId = candQueryDTO.getEntId();
        Boolean work = candQueryDTO.getWork();
        // 企业选择条件
        List<String> candIdList;
        if (StringUtils.isNotEmpty(entId)) {
            candIdList = relationMapper.selectValuesByKey(entId);
        } else {
            candIdList = new ArrayList<>();
        }
        String startTime = getStartTime(candQueryDTO.getTimeQuery());

        IPage<CandidateSimpleBO> pageResult = candidateMapper
                .selectCandSimple(candPage, name, rating, work, candIdList, startTime, DateUtil.currentTimestamp());
        pageResult.getRecords().forEach(item ->
                item.setRating(HrimsConstants.RatingEnum.getLevel(Integer.valueOf(item.getRating()))));

        CandidateRecordVO candidateRecordVO = new CandidateRecordVO();
        candidateRecordVO.setCandidateSimplePage(pageResult);

        Map<String, Integer> interviewCount = new HashMap<>(HrimsConstants.InterviewStateEnum.values().length);
        for (HrimsConstants.InterviewStateEnum e : HrimsConstants.InterviewStateEnum.values()) {
            long count = pageResult.getRecords().stream().filter(o -> e.getCode().equals(o.getState())).count();
            interviewCount.put(e.getDesc(), (int) count);
        }
        candidateRecordVO.setStateCount(interviewCount);
        return candidateRecordVO;
    }

    // ------------------ 内部方法 ------------------ //

    /**
     * 获取起始时间
     *
     * @param timeQuery 时间查询参数
     * @return 起始时间
     */
    private String getStartTime(String timeQuery) {
        HrimsConstants.DateEnum anEnum = HrimsConstants.DateEnum.getEnum(timeQuery);
        if (anEnum == null) {
            return HrimsConstants.START_TIME;
        } else {
            return DateUtil.ago(anEnum);
        }
    }
}
