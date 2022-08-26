package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2022/7/23 23:51
 * @desc 岗位信息持久化
 */
@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取岗位名称
     *
     * @param postId 岗位Id
     * @return 岗位名称
     */
    String selectNameById(String postId);
}
