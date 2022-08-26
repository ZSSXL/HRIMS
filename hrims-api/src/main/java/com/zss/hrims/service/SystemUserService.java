package com.zss.hrims.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.model.dto.LoginDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.RegisterDTO;
import com.zss.hrims.model.entity.SystemUser;
import com.zss.hrims.model.vo.UserDetailVO;
import com.zss.hrims.model.vo.UserVO;

/**
 * @author ZSS
 * @date 2022/7/23 19:43
 * @desc 用户服务层接口
 */
public interface SystemUserService {

    /**
     * 判断用户是否存在
     *
     * @param loginName 登录名
     * @return 存在与否
     */
    Boolean isExistInDb(String loginName);

    /**
     * 创建用户
     *
     * @param user 用户
     * @return 创建是否成功
     */
    Boolean create(SystemUser user);

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 成功与否
     */
    UserVO login(LoginDTO loginDTO);

    /**
     * 注册新用户
     *
     * @param registerDTO 注册信息
     * @return 注册成功与否
     */
    Boolean register(RegisterDTO registerDTO);

    /**
     * 获取普通用户列表
     *
     * @param pageQueryDTO 分页查询信息
     * @return 用户列表
     */
    IPage<UserDetailVO> list(PageQueryDTO pageQueryDTO);
}
