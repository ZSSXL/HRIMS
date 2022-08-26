package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.entity.Enterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 23:48
 * @desc 企业信息持久化
 */
@Mapper
@Repository
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    /**
     * 获取企业名称
     *
     * @param id 企业Id
     * @return enterpriseId
     */
    String selectNameById(String id);

    /**
     * 查询与该企业的合作状态
     *
     * @param entId 企业Id
     * @return 合作状态
     */
    Boolean selectCooperationById(String entId);

    /**
     * 修改合作状态
     *
     * @param entId 企业id
     * @param coop  合作状态
     * @return 修改结果
     */
    int updateCooperationById(@Param("entId") String entId, @Param("coop") boolean coop);
}
