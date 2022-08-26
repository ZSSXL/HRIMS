package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.CandQueryDTO;
import com.zss.hrims.model.dto.CandidateDTO;
import com.zss.hrims.model.dto.UpdateCandidateDTO;
import com.zss.hrims.model.vo.CandidateDetailVO;
import com.zss.hrims.model.vo.CandidateRecordVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.CandidateService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @date 2022/7/24 23:01
 * @desc 候选人控制器
 * 与候选人相关的操作
 * 如候选人的创建，候选人推送企业选择
 */
@RestController
@CheckPermission
@Api(tags = "候选人控制器")
@RequestMapping("/cand")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    @ApiOperation("创建候选人")
    public ServerResponse<String> create(@RequestBody CandidateDTO candidateDTO, BindingResult error) {
        if (error.hasErrors()) {
            return ServerResponse.error("缺少必要参数");
        }
        Boolean exist = candidateService.exist(candidateDTO.getIdNumber());
        if (exist) {
            return ServerResponse.error("该候选人已经添加");
        }
        Boolean result = candidateService.create(candidateDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加失败");
    }

    @PostMapping("/query")
    @ApiOperation("获取候选人列表")
    public ServerResponse<CandidateRecordVO> list(@RequestBody CandQueryDTO candQueryDTO) {
        CandidateRecordVO result = candidateService.query(candQueryDTO);
        return ServerResponse.success(result);
    }

    @GetMapping("/{candId}")
    @ApiOperation("获取候选人的详细信息")
    public ServerResponse<CandidateDetailVO> detail(@PathVariable("candId") String candId) {
        CandidateDetailVO result = candidateService.detail(candId);
        return ServerResponse.success(result);
    }

    @PutMapping
    @ApiOperation("更新候选人基本信息")
    public ServerResponse<CandidateDetailVO> update(@RequestBody UpdateCandidateDTO updateCandidateDTO) {
        if (StringUtils.isEmpty(updateCandidateDTO.getId())) {
            return ServerResponse.error("Id不能为空，请选择要更新的候选人");
        }
        CandidateDetailVO result = candidateService.update(updateCandidateDTO);
        return ReturnUtil.nullReturn(result, "更新失败");
    }
}
