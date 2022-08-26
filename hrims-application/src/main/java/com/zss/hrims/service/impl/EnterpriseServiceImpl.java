package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.CandidateMapper;
import com.zss.hrims.mapper.EnterpriseMapper;
import com.zss.hrims.mapper.EnterprisePostMapper;
import com.zss.hrims.mapper.RelationMapper;
import com.zss.hrims.model.bo.EntPostBO;
import com.zss.hrims.model.dto.BindEntQuery;
import com.zss.hrims.model.dto.EnterpriseDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.UpdateEnterpriseDTO;
import com.zss.hrims.model.entity.Enterprise;
import com.zss.hrims.model.vo.BindEntVO;
import com.zss.hrims.model.vo.EnterpriseDetailVO;
import com.zss.hrims.model.vo.EnterpriseSimpleVO;
import com.zss.hrims.service.EnterpriseService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.GeneralConverter;
import com.zss.hrims.utils.PageUtil;
import com.zss.hrims.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @date 2022/7/25 9:47
 * @desc 企业服务层接口方法实现
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseMapper enterpriseMapper;
    private final EnterprisePostMapper enterprisePostMapper;
    private final CandidateMapper candidateMapper;
    private final RelationMapper relationMapper;
    private final GeneralConverter converter;

    @Autowired
    public EnterpriseServiceImpl(EnterpriseMapper enterpriseMapper, EnterprisePostMapper enterprisePostMapper, CandidateMapper candidateMapper, RelationMapper relationMapper, GeneralConverter converter) {
        this.enterpriseMapper = enterpriseMapper;
        this.enterprisePostMapper = enterprisePostMapper;
        this.candidateMapper = candidateMapper;
        this.relationMapper = relationMapper;
        this.converter = converter;
    }

    @Override
    public Boolean create(EnterpriseDTO enterpriseDTO) {
        Enterprise converter = this.converter.converter(enterpriseDTO, Enterprise.class);
        converter.setId(UUIDUtil.getId());
        converter.setCooperation(true);
        converter.setCreateTime(DateUtil.currentTimestamp());
        return enterpriseMapper.insert(converter) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String entId) {
        return enterpriseMapper.deleteById(entId) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean cooperation(String entId) {
        Boolean coop = enterpriseMapper.selectCooperationById(entId);
        if (coop) {
            return enterpriseMapper.updateCooperationById(entId, false) == 1;
        } else {
            return enterpriseMapper.updateCooperationById(entId, true) == 1;
        }
    }

    @Override
    public IPage<Enterprise> list(PageQueryDTO pageQueryDTO) {
        IPage<Enterprise> entPage = PageUtil.of(pageQueryDTO.getPageIndex(), pageQueryDTO.getPageSize());
        QueryWrapper<Enterprise> query = PageUtil.orderBy("create_time", pageQueryDTO.getSort());
        query.like("name", StringUtils.isEmpty(pageQueryDTO.getName()) ? StringUtils.EMPTY : pageQueryDTO.getName());
        IPage<Enterprise> page = enterpriseMapper.selectPage(entPage, query);
        List<Enterprise> records = page.getRecords();
        for (Enterprise ent : records) {
            ent.setCreateTime(DateUtil.timestampToDate(ent.getCreateTime()));
        }
        page.setRecords(records);
        return page;
    }

    @Override
    public List<EnterpriseSimpleVO> entSimpleList(String userId) {
        List<String> entIds = relationMapper.selectValuesByKey(userId);
        QueryWrapper<Enterprise> query = new QueryWrapper<>();
        query.in("id", entIds)
                // 仅限正在合作的企业
                .eq("cooperation", true).orderByDesc("create_time");
        List<Enterprise> enterpriseList = enterpriseMapper.selectList(query);
        return converter.converter(enterpriseList, EnterpriseSimpleVO.class);
    }

    @Override
    public EnterpriseDetailVO entDetail(String entId) {
        QueryWrapper<Enterprise> query = new QueryWrapper<>();
        query.in("id", entId);
        Enterprise enterprise = enterpriseMapper.selectOne(query);
        if (enterprise == null) {
            return null;
        }
        EnterpriseDetailVO entDetailVO = this.converter.converter(enterprise, EnterpriseDetailVO.class);

        List<EntPostBO> postList = enterprisePostMapper.selectPostByEntId(entId);

        Map<String, Map<String, Integer>> postMap = new HashMap<>(postList.size());
        for (EntPostBO post : postList) {
            Map<String, Integer> interviewCount = new HashMap<>(HrimsConstants.InterviewStateEnum.values().length);
            for (HrimsConstants.InterviewStateEnum e : HrimsConstants.InterviewStateEnum.values()) {
                Integer count = candidateMapper.countCandByState(post.getPostId(), entId, e.getCode());
                interviewCount.put(e.getDesc(), count);
            }
            postMap.put(post.getPostName(), interviewCount);
        }
        entDetailVO.setPostCount(postMap);
        return entDetailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UpdateEnterpriseDTO updateEnterpriseDTO) {
        UpdateWrapper<Enterprise> update = new UpdateWrapper<>();
        update.eq("id", update);
        if (StringUtils.isNotEmpty(updateEnterpriseDTO.getName())) {
            update.set("name", updateEnterpriseDTO.getName());
        }
        if (StringUtils.isNotEmpty(updateEnterpriseDTO.getAddress())) {
            update.set("address", updateEnterpriseDTO.getAddress());
        }
        if (StringUtils.isNotEmpty(updateEnterpriseDTO.getPrincipal())) {
            update.set("principal", updateEnterpriseDTO.getPrincipal());
        }
        if (StringUtils.isNotEmpty(updateEnterpriseDTO.getPhone())) {
            update.set("phone", updateEnterpriseDTO.getPhone());
        }
        return enterpriseMapper.update(null, update) == 1;
    }

    @Override
    public List<BindEntVO> getBindEnt(BindEntQuery bindEntQuery) {
        List<String> entIdList = relationMapper.selectValuesByKey(bindEntQuery.getUserId());

        // 查询已绑定的
        QueryWrapper<Enterprise> query = new QueryWrapper<>();
        query.orderByDesc("create_time");

        List<Enterprise> enterpriseList = enterpriseMapper.selectList(query);

        List<BindEntVO> result = new ArrayList<>(enterpriseList.size());
        enterpriseList.forEach(item -> {
            BindEntVO bindEntVO = BindEntVO.builder()
                    .entId(item.getId())
                    .entName(item.getName())
                    .bind(entIdList.contains(item.getId()))
                    .build();
            result.add(bindEntVO);
        });
        return result;
    }
}
