package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.ResumeSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/26 16:09
 * @desc 简历来源
 */
@RestController
@CheckPermission
@Api(tags = "简历来源")
@RequestMapping("/resume")
public class ResumeSourceController {

    private final ResumeSourceService resumeSourceService;

    @Autowired
    public ResumeSourceController(ResumeSourceService resumeSourceService) {
        this.resumeSourceService = resumeSourceService;
    }

    @GetMapping
    @ApiOperation("简历来源列表")
    public ServerResponse<List<String>> list() {
        List<String> sourceList = resumeSourceService.list();
        return ServerResponse.success(sourceList);
    }
}
