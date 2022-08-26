package com.zss.hrims.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.SystemUserMapper;
import com.zss.hrims.model.dto.LoginDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.RegisterDTO;
import com.zss.hrims.model.entity.SystemUser;
import com.zss.hrims.model.vo.UserDetailVO;
import com.zss.hrims.model.vo.UserVO;
import com.zss.hrims.service.SystemUserService;
import com.zss.hrims.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 19:44
 * @desc 用户层方法实现
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserMapper systemUserMapper;
    private final GeneralConverter converter;

    @Autowired
    public SystemUserServiceImpl(SystemUserMapper systemUserMapper, GeneralConverter converter) {
        this.systemUserMapper = systemUserMapper;
        this.converter = converter;
    }

    @Override
    public Boolean create(SystemUser user) {
        return systemUserMapper.insert(user) == 1;
    }

    @Override
    public Boolean isExistInDb(String loginName) {
        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginName);
        SystemUser systemUser = systemUserMapper.selectOne(wrapper);
        return systemUser != null;
    }

    @Override
    public UserVO login(LoginDTO loginDTO) {
        QueryWrapper<SystemUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginDTO.getLoginName());
        wrapper.eq("password", EncryptionUtil.encrypt(loginDTO.getPassword()));
        SystemUser systemUser = systemUserMapper.selectOne(wrapper);
        if (systemUser == null) {
            return null;
        } else {
            return UserVO.builder()
                    .userId(systemUser.getUserId())
                    .username(systemUser.getUsername())
                    .role(systemUser.getRole())
                    .createTime(DateUtil.timestampToDate(systemUser.getCreateTime()))
                    .build();
        }
    }

    @Override
    public Boolean register(RegisterDTO registerDTO) {
        SystemUser user = this.converter.converter(registerDTO, SystemUser.class);
        user.setUserId(UUIDUtil.getId());
        user.setRole(HrimsConstants.Role.ORDINARY);
        user.setPassword(EncryptionUtil.encrypt(user.getPassword()));
        user.setCreateTime(DateUtil.currentTimestamp());
        return systemUserMapper.insert(user) == 1;
    }

    @Override
    public IPage<UserDetailVO> list(PageQueryDTO pageQueryDTO) {
        IPage<SystemUser> entPage = PageUtil.
                of(pageQueryDTO.getPageIndex(), pageQueryDTO.getPageSize());
        QueryWrapper<SystemUser> query = PageUtil.orderBy("create_time", pageQueryDTO.getSort());
        query.eq("role", HrimsConstants.Role.ORDINARY)
                .like("username", StringUtils.isEmpty(pageQueryDTO.getName()) ? StringUtils.EMPTY : pageQueryDTO.getName());
        IPage<SystemUser> page = systemUserMapper.selectPage(entPage, query);
        List<UserDetailVO> userDetailVOList = new ArrayList<>(page.getRecords().size());
        page.getRecords().forEach(item -> {
            item.setPassword("********");
            UserDetailVO converter = this.converter.converter(item, UserDetailVO.class);
            converter.setPassword(EncryptionUtil.encrypt(converter.getPassword()));
            converter.setCreateTime(DateUtil.timestampToDate(item.getCreateTime()));
            userDetailVOList.add(converter);
        });
        // 重新初始化对象
        Page<UserDetailVO> detailPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        detailPage.setRecords(userDetailVOList);
        return detailPage;
    }
}
