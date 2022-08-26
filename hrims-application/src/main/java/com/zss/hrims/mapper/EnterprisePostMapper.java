package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.bo.EntPostBO;
import com.zss.hrims.model.entity.EnterprisePost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 23:50
 * @desc 企业岗位信息持久化
 */
@Mapper
@Repository
public interface EnterprisePostMapper extends BaseMapper<EnterprisePost> {

    /**
     * 获取对应企业分配的岗位
     *
     * @param entId 企业Id
     * @return 企业岗位列表
     */
    List<EntPostBO> selectPostByEntId(String entId);
}
