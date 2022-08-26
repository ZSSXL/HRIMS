package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.bo.RelationBO;
import com.zss.hrims.model.entity.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 23:52
 * @desc 关系数据持久化
 */
@Mapper
@Repository
public interface RelationMapper extends BaseMapper<Relation> {

    /**
     * 根绝value获取对应的key
     *
     * @param value 值
     * @return key
     */
    String selectKeyByValue(String value);

    /**
     * 根据key查询关联的Value
     *
     * @param key 键
     * @return values
     */
    List<String> selectValuesByKey(String key);

    /**
     * 查询用户与企业的对应关系
     *
     * @param key       userId
     * @return relations
     */
    List<RelationBO> queryU2E(String key);

    /**
     * 查询企业与候选人的对应关系
     *
     * @param key       entId
     * @return relations
     */
    List<RelationBO> queryE2C(String key);
}
