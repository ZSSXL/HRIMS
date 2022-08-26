package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.vo.PostVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/25 23:14
 * @desc 岗位控制器
 */
@RestController
@CheckPermission
@Api(tags = "岗位控制器")
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ApiOperation("获取岗位列表")
    public ServerResponse<List<PostVO>> list(@RequestParam(required = false) String name) {
        List<PostVO> postVOList = postService.getAllPost(name);
        return ServerResponse.success(postVOList);
    }
}
