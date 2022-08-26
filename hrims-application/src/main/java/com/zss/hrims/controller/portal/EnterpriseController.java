package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.model.dto.EnterprisePostDTO;
import com.zss.hrims.model.vo.EnterpriseDetailVO;
import com.zss.hrims.model.vo.EnterpriseSimpleVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.EnterprisePostService;
import com.zss.hrims.service.EnterpriseService;
import com.zss.hrims.utils.ReturnUtil;
import com.zss.hrims.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ZSS
 * @date 2022/7/24 22:59
 * @desc 企业控制器
 * 有关企业相关的操作
 * 如面试情况，岗位添加
 */
@RestController
@CheckPermission
@Api(tags = "企业控制器")
@RequestMapping("/ent")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;
    private final EnterprisePostService enterprisePostService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, EnterprisePostService enterprisePostService) {
        this.enterpriseService = enterpriseService;
        this.enterprisePostService = enterprisePostService;
    }

    @PostMapping("/post")
    @ApiOperation("企业添加职业")
    public ServerResponse<String> createEntPost(@RequestBody EnterprisePostDTO enterprisePostDTO) {
        if (StringUtils.isEmpty(enterprisePostDTO.getEnterpriseId())) {
            return ServerResponse.error("请选择企业");
        }
        if (StringUtils.isEmpty(enterprisePostDTO.getPostId())) {
            return ServerResponse.error("请选择岗位");
        }
        Boolean result = enterprisePostService.create(enterprisePostDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加失败");
    }

    @DeleteMapping("/{entId}/{postId}")
    @ApiOperation("从此公司中移除该岗位")
    public ServerResponse<String> removeEntPost(@PathVariable("entId") String entId, @PathVariable("postId") String postId) {
        Boolean result = enterprisePostService.removeEntPost(entId, postId);
        return ReturnUtil.boolReturn(result, "移除成功", "移除失败");
    }

    @GetMapping
    @ApiOperation("获取当前用户负责的企业")
    public ServerResponse<List<EnterpriseSimpleVO>> entList(@RequestHeader(HrimsConstants.HRIMS_TOKEN) String token) {
        String userId = TokenUtil.getClaim(token, "userId").asString();
        List<EnterpriseSimpleVO> entList = enterpriseService.entSimpleList(userId);
        return ServerResponse.success(entList);
    }

    @GetMapping("/{entId}")
    @ApiOperation("获取当前企业的招聘详情")
    public ServerResponse<EnterpriseDetailVO> entDetail(@PathVariable("entId") String entId) {
        EnterpriseDetailVO result = enterpriseService.entDetail(entId);
        return ServerResponse.success(result);
    }
}
