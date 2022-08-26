package com.zss.hrims.controller.system;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.RelationDTO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.RelationService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @date 2022/8/15 16:48
 * @desc 关系控制器
 */
@RestController
@Api(tags = "关系控制器")
@CheckPermission(admin = true)
@RequestMapping("/sys/relation")
public class SysRelationController {

    private final RelationService relationService;

    public SysRelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @DeleteMapping("/{relationId}")
    @ApiOperation("移除关系")
    public ServerResponse<String> remove(@PathVariable("relationId") String relationId) {
        Boolean result = relationService.remove(relationId);
        return ReturnUtil.boolReturn(result, "解除关系成功", "解除关系失败");
    }

    @PostMapping("/release")
    @ApiOperation("批量移除关系")
    public ServerResponse<String> removeByValues(@RequestBody RelationDTO relationDTO) {
        Boolean result = relationService.removeByValues(relationDTO);
        return ReturnUtil.boolReturn(result, "解除关系成功", "略有纰漏");
    }

    @PostMapping("/dist")
    @ApiOperation("分配关系")
    public ServerResponse<String> distributionEnt(@RequestBody RelationDTO relationDTO) {
        if (StringUtils.isEmpty(relationDTO.getKey())) {
            return ServerResponse.error("请选择要分配的目标");
        }
        if (relationDTO.getValues().isEmpty()) {
            return ServerResponse.error("请至少选择一个要分配的对象");
        }
        Boolean result = relationService.create(relationDTO);
        return ReturnUtil.boolReturn(result, "分配成功", "略有纰漏");
    }
}
