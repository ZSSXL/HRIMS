package com.zss.hrims.controller.system;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.PostDTO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.PostService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @date 2022/7/24 23:05
 * @desc 职业岗位控制器
 */
@RestController
@Api(tags = "岗位职业控制器")
@RequestMapping("/sys/post")
@CheckPermission(admin = true)
public class SysPostController {

    private final PostService postService;

    @Autowired
    public SysPostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ApiOperation("添加岗位")
    public ServerResponse<String> create(@RequestBody PostDTO postDTO) {
        if (StringUtils.isEmpty(postDTO.getName())) {
            return ServerResponse.error("岗位名称不能为空");
        }
        Boolean exist = postService.exist(postDTO.getName());
        if (exist) {
            return ServerResponse.error("该岗位已存在");
        }
        Boolean result = postService.create(postDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加失败");
    }

    @DeleteMapping("/{postId}")
    @ApiOperation("删除岗位")
    public ServerResponse<String> delete(@PathVariable("postId") String postId) {
        Boolean result = postService.delete(postId);
        return ReturnUtil.boolReturn(result, "删除成功", "删除失败");
    }
}
