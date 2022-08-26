package com.zss.hrims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zss.hrims.model.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ZSS
 * @date 2022/7/22 15:18
 * @desc 系统用户持久化
 */
@Mapper
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    /**
     * 通过用户id获取用户名
     *
     * @param userId 用户id
     * @return 用户名
     */
    String queryUsernameById(@Param("userId") String userId);
}
