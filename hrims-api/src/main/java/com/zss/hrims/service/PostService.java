package com.zss.hrims.service;

import com.zss.hrims.model.dto.PostDTO;
import com.zss.hrims.model.vo.PostVO;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:45
 * @desc 岗位服务层接口
 */
public interface PostService {

    /**
     * 添加岗位
     *
     * @param postDTO 岗位星系
     * @return 是否创建成功
     */
    Boolean create(PostDTO postDTO);

    /**
     * 获取岗位列表
     *
     * @param name 岗位名称
     */
    List<PostVO> getAllPost(String name);

    /**
     * 删除岗位
     *
     * @param postId 岗位Id
     * @return 是否删除成功
     */
    Boolean delete(String postId);

    /**
     * 判断该岗位是否存在
     *
     * @param name 岗位名称
     * @return 是否存在
     */
    Boolean exist(String name);
}
