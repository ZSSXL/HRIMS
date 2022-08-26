package com.zss.hrims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.model.dto.EnterpriseDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.BindEntQuery;
import com.zss.hrims.model.dto.UpdateEnterpriseDTO;
import com.zss.hrims.model.entity.Enterprise;
import com.zss.hrims.model.vo.EnterpriseDetailVO;
import com.zss.hrims.model.vo.EnterpriseSimpleVO;
import com.zss.hrims.model.vo.BindEntVO;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 9:46
 * @desc 企业服务层接口
 */
public interface EnterpriseService {

    /**
     * 添加新企业
     *
     * @param enterpriseDTO 企业基本信息
     * @return 是否创建成功
     */
    Boolean create(EnterpriseDTO enterpriseDTO);

    /**
     * 删除企业
     *
     * @param entId 企业id
     * @return 是否成功删除
     */
    Boolean delete(String entId);

    /**
     * 终止或开始合作
     *
     * @param entId 企业Id
     * @return 合作状态
     */
    Boolean cooperation(String entId);

    /**
     * 获取企业列表
     *
     * @param pageQueryDTO 分页信息
     * @return entList
     */
    IPage<Enterprise> list(PageQueryDTO pageQueryDTO);

    /**
     * 获取用户所负责的企业列表
     *
     * @param userId 用户Id
     * @return entSimpleList
     */
    List<EnterpriseSimpleVO> entSimpleList(String userId);

    /**
     * 获取企业招聘详情
     *
     * @param entId 企业Id
     * @return 招聘详情
     */
    EnterpriseDetailVO entDetail(String entId);

    /**
     * 更新企业信息
     *
     * @param updateEnterpriseDTO 企业信息
     * @return 是否更新成功
     */
    Boolean update(UpdateEnterpriseDTO updateEnterpriseDTO);

    /**
     * 查询绑定的企业
     *
     * @param bindEntQuery 查询条件
     * @return 绑定的企业
     */
    List<BindEntVO> getBindEnt(BindEntQuery bindEntQuery);
}
