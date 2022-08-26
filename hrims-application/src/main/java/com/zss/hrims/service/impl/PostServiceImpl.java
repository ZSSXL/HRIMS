package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zss.hrims.mapper.PostMapper;
import com.zss.hrims.model.dto.PostDTO;
import com.zss.hrims.model.entity.Post;
import com.zss.hrims.model.vo.PostVO;
import com.zss.hrims.service.PostService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.GeneralConverter;
import com.zss.hrims.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 13:45
 * @desc 岗位服务层接口方法实现
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final GeneralConverter converter;

    @Autowired
    public PostServiceImpl(PostMapper postMapper, GeneralConverter converter) {
        this.postMapper = postMapper;
        this.converter = converter;
    }

    @Override
    public Boolean create(PostDTO postDTO) {
        Post post = Post.builder()
                .id(UUIDUtil.getId())
                .name(postDTO.getName())
                .describe(postDTO.getDescribe())
                .createTime(DateUtil.currentTimestamp())
                .build();
        return postMapper.insert(post) == 1;
    }

    @Override
    public List<PostVO> getAllPost(String name) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("create_time")
                .like("name", StringUtils.isEmpty(name) ? "" : name);
        List<Post> postList = postMapper.selectList(queryWrapper);
        return converter.converter(postList, PostVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(String postId) {
        return postMapper.deleteById(postId) == 1;
    }

    @Override
    public Boolean exist(String name) {
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.eq("name", name);
        return postMapper.selectCount(query) == 1;
    }
}
