package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.bo.AttachmentBO;
import com.zss.hrims.model.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/8/24 11:11
 * @desc 附件实体持久化
 */
@Mapper
@Repository
public interface AttachmentMapper extends BaseMapper<Attachment> {

    /**
     * 查询文件全路径
     *
     * @param key 所属
     * @return fullPaths
     */
    List<String> queryFullPathByKey(String key);

    /**
     * 查询所属附件
     *
     * @param key 所属
     * @return attachList
     */
    List<AttachmentBO> selectByKey(String key);
}
