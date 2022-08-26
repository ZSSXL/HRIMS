package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zss.hrims.mapper.EnterprisePostMapper;
import com.zss.hrims.model.dto.EnterprisePostDTO;
import com.zss.hrims.model.entity.EnterprisePost;
import com.zss.hrims.service.EnterprisePostService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.GeneralConverter;
import com.zss.hrims.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZSS
 * @date 2022/7/25 23:07
 * @desc 企业岗位服务层接口方法实现
 */
@Service
public class EnterprisePostServiceImpl implements EnterprisePostService {

    private final EnterprisePostMapper enterprisePostMapper;
    private final GeneralConverter converter;

    @Autowired
    public EnterprisePostServiceImpl(EnterprisePostMapper enterprisePostMapper, GeneralConverter converter) {
        this.enterprisePostMapper = enterprisePostMapper;
        this.converter = converter;
    }

    @Override
    public Boolean create(EnterprisePostDTO enterprisePostDTO) {
        EnterprisePost enterprisePost = this.converter.converter(enterprisePostDTO, EnterprisePost.class);
        enterprisePost.setId(UUIDUtil.getId());
        enterprisePost.setCreateTime(DateUtil.currentTimestamp());
        return enterprisePostMapper.insert(enterprisePost) == 1;
    }

    @Override
    public Boolean removeEntPost(String entId, String postId) {
        QueryWrapper<EnterprisePost> delete = new QueryWrapper<>();
        delete.eq("enterprise_id", entId)
                .eq("post_id", postId);
        return enterprisePostMapper.delete(delete) == 1;
    }
}
