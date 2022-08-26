package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.dto.InterviewDTO;
import com.zss.hrims.model.dto.UpdateInterviewDTO;
import com.zss.hrims.model.vo.InterviewVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.InterviewService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZSS
 * @date 2022/7/24 23:06
 * @desc 面试控制器
 * 对候选人面试的相关操作
 * 如创建面试，填写面试结果，面试结果统计
 */
@RestController
@CheckPermission
@Api(tags = "面试控制器")
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    @ApiOperation("添加面试")
    public ServerResponse<String> create(@RequestBody InterviewDTO interviewDTO) {
        if (StringUtils.isEmpty(interviewDTO.getCandidateId())) {
            return ServerResponse.error("请选择要面试的候选人");
        }

        Boolean next = interviewService.state(interviewDTO.getCandidateId());
        if (!next) {
            return ServerResponse.error("当前不进行下一步面试");
        }

        Boolean result = interviewService.create(interviewDTO);
        return ReturnUtil.boolReturn(result, "添加成功", "添加失败");
    }

    @PutMapping
    @ApiOperation("更新面试信息")
    public ServerResponse<InterviewVO> update(@RequestBody UpdateInterviewDTO updateDTO) {
        if (StringUtils.isEmpty(updateDTO.getId())) {
            return ServerResponse.error("Id为空，请选择正确的面试");
        }
        InterviewVO interviewVO = interviewService.update(updateDTO);
        return ReturnUtil.nullReturn(interviewVO, "更新失败");
    }
}
