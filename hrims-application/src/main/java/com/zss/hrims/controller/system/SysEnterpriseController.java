package com.zss.hrims.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.EnterpriseDTO;
import com.zss.hrims.model.dto.PageQueryDTO;
import com.zss.hrims.model.dto.BindEntQuery;
import com.zss.hrims.model.dto.UpdateEnterpriseDTO;
import com.zss.hrims.model.entity.Enterprise;
import com.zss.hrims.model.vo.BindEntVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.EnterpriseService;
import com.zss.hrims.utils.ReturnUtil;
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
 */
@RestController
@Api(tags = "企业控制器")
@RequestMapping("/sys/ent")
@CheckPermission(admin = true)
public class SysEnterpriseController {

    private final EnterpriseService enterpriseService;

    @Autowired
    public SysEnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping
    @ApiOperation("添加企业")
    public ServerResponse<String> createEnt(@RequestBody EnterpriseDTO enterpriseDTO) {
        if (StringUtils.isEmpty(enterpriseDTO.getName())) {
            return ServerResponse.error("企业名称不能为空");
        }
        Boolean result = enterpriseService.create(enterpriseDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加企业失败");
    }

    @DeleteMapping("/{entId}")
    @ApiOperation("删除企业")
    public ServerResponse<String> deleteEnt(@PathVariable("entId") String entId) {
        Boolean result = enterpriseService.delete(entId);
        return ReturnUtil.boolReturn(result, "删除成功", "删除企业失败");
    }

    @PutMapping("/{entId}")
    @ApiOperation("停止合作or开始合作")
    public ServerResponse<String> cooperation(@PathVariable("entId") String entId) {
        Boolean result = enterpriseService.cooperation(entId);
        return ReturnUtil.boolReturn(result, "操作成功", "操作失败");
    }

    @PutMapping
    @ApiOperation("更行企业信息")
    public ServerResponse<String> update(@RequestBody UpdateEnterpriseDTO updateEnterpriseDTO) {
        if (StringUtils.isEmpty(updateEnterpriseDTO.getId())) {
            return ServerResponse.error("企业ID不能为空");
        }
        Boolean result = enterpriseService.update(updateEnterpriseDTO);
        return ReturnUtil.boolReturn(result, "更新成功", "更新失败");
    }

    @PostMapping("/list")
    @ApiOperation("获取企业列表")
    public ServerResponse<IPage<Enterprise>> entList(@RequestBody PageQueryDTO pageQueryDTO) {
        IPage<Enterprise> page = enterpriseService.list(pageQueryDTO);
        return ServerResponse.success(page);
    }

    @PostMapping("/unbind")
    @ApiOperation("获取用户未绑定的企业列表")
    public ServerResponse<List<BindEntVO>> bindEntList(@RequestBody BindEntQuery bindEntQuery) {
        if (StringUtils.isEmpty(bindEntQuery.getUserId())) {
            return ServerResponse.error("id不能为空");
        }
        List<BindEntVO> result = enterpriseService.getBindEnt(bindEntQuery);
        return ServerResponse.success(result);
    }
}
