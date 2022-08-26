package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.RelationMapper;
import com.zss.hrims.model.bo.RelationBO;
import com.zss.hrims.model.dto.RelationDTO;
import com.zss.hrims.model.dto.RemoveRelationDTO;
import com.zss.hrims.model.entity.Relation;
import com.zss.hrims.model.vo.RelationVO;
import com.zss.hrims.service.RelationService;
import com.zss.hrims.utils.GeneralConverter;
import com.zss.hrims.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:18
 * @desc 关系服务层接口方法实现
 */
@Slf4j
@Service
public class RelationServiceImpl implements RelationService {

    private final RelationMapper relationMapper;
    private final GeneralConverter converter;

    @Autowired
    public RelationServiceImpl(RelationMapper relationMapper, GeneralConverter converter) {
        this.relationMapper = relationMapper;
        this.converter = converter;
    }

    @Override
    public Boolean create(RelationDTO relationDTO) {
        int count = 0;
        for (String value : relationDTO.getValues()) {
            Relation build = Relation.builder()
                    .id(UUIDUtil.getId())
                    .key(relationDTO.getKey())
                    .value(value)
                    .build();
            count += relationMapper.insert(build);
        }
        log.info("Relation: ValueSize->[{}], Count->[{}]", relationDTO.getValues().size(), count);
        return count == relationDTO.getValues().size();
    }

    @Override
    public List<RelationVO> getRelations(String key, HrimsConstants.RelationEnum type) {
        List<RelationBO> result = new ArrayList<>();
        switch (type) {
            case U2E:
                result = relationMapper.queryU2E(key);
                break;
            case E2C:
                result = relationMapper.queryE2C(key);
                break;
            case C2I:
                System.out.println("暂无");
                break;
            default:
                return new ArrayList<>();
        }
        return converter.converter(result, RelationVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remove(String relationId) {
        return relationMapper.deleteById(relationId) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeByValues(RelationDTO relationDTO) {
        QueryWrapper<Relation> delete = new QueryWrapper<>();
        delete.eq("key", relationDTO.getKey())
                .in("value", relationDTO.getValues());
        int deleteResult = relationMapper.delete(delete);
        log.info("ReadyToDelete: [{}], ActuallyDelete: [{}]", relationDTO.getValues().size(), deleteResult);
        return true;
    }
}
