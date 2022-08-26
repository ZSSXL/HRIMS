package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.bo.ResumeSourceBO;
import com.zss.hrims.model.entity.ResumeSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/24 23:22
 * @desc 建立来源数据持久化
 */
@Mapper
@Repository
public interface ResumeSourceMapper extends BaseMapper<ResumeSource> {

    /**
     * 获取简历来源列表
     *
     * @return 简历来源列表
     */
    List<ResumeSourceBO> selectResumeSource();
}
