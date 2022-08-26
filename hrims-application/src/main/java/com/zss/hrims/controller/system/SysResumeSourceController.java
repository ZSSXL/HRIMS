package com.zss.hrims.controller.system;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.ResumeSourceDTO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.ResumeSourceService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZSS
 * @date 2022/7/25 9:24
 * @desc 系统简历来源控制器
 * 简历来源的相关操作
 */
@RestController
@CheckPermission(admin = true)
@Api(tags = "系统简历来源控制器")
@RequestMapping("/sys/resume")
public class SysResumeSourceController {
    private final ResumeSourceService resumeSourceService;

    @Autowired
    public SysResumeSourceController(ResumeSourceService resumeSourceService) {
        this.resumeSourceService = resumeSourceService;
    }

    @PostMapping
    @ApiOperation("添加简历来源")
    public ServerResponse<String> create(@RequestBody ResumeSourceDTO resumeSourceDTO) {
        if (StringUtils.isEmpty(resumeSourceDTO.getSource())) {
            return ServerResponse.error("简历来源不能为空");
        }
        Boolean result = resumeSourceService.create(resumeSourceDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加失败");
    }

}
