package com.zss.hrims.controller.portal;

import com.zss.hrims.anno.CheckPermission;
import com.zss.hrims.model.vo.ImagePreviewVO;
import com.zss.hrims.response.ServerResponse;
import com.zss.hrims.service.AttachmentService;
import com.zss.hrims.utils.ReturnUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ZSS
 * @date 2022/8/23 13:52
 * @desc 附件原理
 */
@Slf4j
@RestController
@CheckPermission
@Api(tags = "候选人附件")
@RequestMapping("/attach")
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping
    @ApiOperation("上传文件")
    public ServerResponse<ImagePreviewVO> upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("image");
        if (file == null) {
            return ServerResponse.error("上传文件不能为空");
        }
        // 此key可以是candId
        String key = multipartHttpServletRequest.getParameter("key");
        if (StringUtils.isEmpty(key)) {
            return ServerResponse.error("key不能为空");
        }
        ImagePreviewVO result;
        try {
            result = attachmentService.upload(file.getInputStream(), file.getOriginalFilename(),
                    file.getSize(), key, file.getContentType());
        } catch (IOException e) {
            return ServerResponse.error("上传失败");
        }
        return ServerResponse.success(result);
    }

    @DeleteMapping("/{attachId}")
    public ServerResponse<String> remove(@PathVariable("attachId") String attachId) {
        Boolean result = attachmentService.remove(attachId);
        return ReturnUtil.boolReturn(result, "删除成功", "删除失败");
    }
}
