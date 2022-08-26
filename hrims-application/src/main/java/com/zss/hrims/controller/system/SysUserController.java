package com.zss.hrims.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.model.dto.LoginDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.RegisterDTO;
import com.zss.hrims.model.vo.RelationVO;
import com.zss.hrims.model.vo.UserDetailVO;
import com.zss.hrims.model.vo.UserVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.RelationService;
import com.zss.hrims.service.SystemUserService;
import com.zss.hrims.utils.MapUtil;
import com.zss.hrims.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/23 19:54
 * @desc 系统用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/sys")
@Api(tags = "系统管理员控制器")
public class SysUserController {

    private final SystemUserService systemUserService;
    private final RelationService relationService;

    @Autowired
    public SysUserController(SystemUserService systemUserService, RelationService relationService) {
        this.systemUserService = systemUserService;
        this.relationService = relationService;
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public ServerResponse<HashMap<String, String>> login(@RequestBody LoginDTO loginDTO) {
        UserVO userVO = systemUserService.login(loginDTO);
        if (userVO == null) {
            return ServerResponse.error("登录失败，密码或用户名错误!");
        } else {
            String token = TokenUtil.createToken(MapUtil.create(
                    "userId", userVO.getUserId(),
                    "role", String.valueOf(userVO.getRole())
            ));
            HashMap<String, String> result = MapUtil.create(
                    "username", userVO.getUsername(),
                    "token", token,
                    "role", String.valueOf(userVO.getRole())
            );
            return ServerResponse.success(result);
        }
    }

    @PostMapping("/register")
    @ApiOperation("添加用户")
    @CheckPermission(admin = true)
    public ServerResponse<Boolean> register(@RequestBody RegisterDTO registerDTO) {
        if (StringUtils.isEmpty(registerDTO.getPassword())) {
            return ServerResponse.error("密码不能为空");
        }
        if (StringUtils.isEmpty(registerDTO.getLoginName())) {
            return ServerResponse.error("登录名不能为空");
        }
        if (systemUserService.isExistInDb(registerDTO.getLoginName())) {
            return ServerResponse.error("登录名[" + registerDTO.getLoginName() + "]已存在");
        }
        return ServerResponse.success(systemUserService.register(registerDTO));
    }

    @PostMapping("/ordinary")
    @ApiOperation("获取用户列表")
    @CheckPermission(admin = true)
    public ServerResponse<IPage<UserDetailVO>> userList(@RequestBody PageQueryDTO pageQueryDTO) {
        IPage<UserDetailVO> result = systemUserService.list(pageQueryDTO);
        return ServerResponse.success(result);
    }

    @GetMapping("/{userId}")
    @ApiOperation("获取用户负责的企业")
    @CheckPermission(admin = true)
    public ServerResponse<List<RelationVO>> userEnt(@PathVariable("userId") String userId){
        List<RelationVO> userEntList = relationService.getRelations(userId, HrimsConstants.RelationEnum.U2E);
        return ServerResponse.success(userEntList);
    }
}
