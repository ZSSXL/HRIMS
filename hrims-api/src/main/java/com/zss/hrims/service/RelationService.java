package com.zss.hrims.service;

import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.model.dto.RelationDTO;
import com.zss.hrims.model.dto.RemoveRelationDTO;
import com.zss.hrims.model.vo.RelationVO;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:18
 * @desc 关系服务层接口
 */
public interface RelationService {

    /**
     * 创建关系
     *
     * @param relationDTO 关系对象
     * @return 是否创建成功
     */
    Boolean create(RelationDTO relationDTO);

    /**
     * 获取关系信息
     *
     * @param key  键
     * @param type 关系类型
     * @return 关系结果
     */
    List<RelationVO> getRelations(String key, HrimsConstants.RelationEnum type);

    /**
     * 移除关系
     *
     * @param relationId 移除关系
     * @return 是否移除成功
     */
    Boolean remove(String relationId);

    /**
     * 批量移除关系
     *
     * @param relationDTO 关系
     * @return 移除结果
     */
    Boolean removeByValues(RelationDTO relationDTO);
}
