package com.zss.hrims.service.impl;

import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.mapper.AttachmentMapper;
import com.zss.hrims.model.entity.Attachment;
import com.zss.hrims.model.vo.ImagePreviewVO;
import com.zss.hrims.service.AttachmentService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.MinioUtil;
import com.zss.hrims.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author ZSS
 * @date 2022/8/23 16:26
 * @desc 附件服务层接口方法实现
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final MinioUtil minioUtil;
    private final AttachmentMapper attachmentMapper;

    @Autowired
    public AttachmentServiceImpl(MinioUtil minioUtil, AttachmentMapper attachmentMapper) {
        this.minioUtil = minioUtil;
        this.attachmentMapper = attachmentMapper;
    }

    @Override
    public ImagePreviewVO upload(InputStream inputStream, String fileName, long fileSize, String key, String contentType) {
        // xxx -> xxx/
        String keyPath = key + HrimsConstants.SEPARATOR;

        int index = 1;
        // 此处暂定images文件夹，以后要是有其他类型的文件，再做规划
        String fullPath = "images/" + keyPath + fileName;
        // 处理重复文件名
        while (minioUtil.doesFileExist(fullPath)) {
            String[] fileNameSplit = fileName.split("\\.");
            fullPath = "images/" + keyPath + fileNameSplit[0] + "(" + index + ")" + "." + fileNameSplit[1];
            index++;
        }
        Boolean result = minioUtil.upload(inputStream, fullPath, fileSize, contentType);
        if (result) {
            String attachmentId = UUIDUtil.getId();
            Attachment attachment = Attachment.builder()
                    .id(attachmentId)
                    .key(key)
                    .fullPath(fullPath)
                    .createTime(DateUtil.currentTimestamp())
                    .build();
            attachmentMapper.insert(attachment);
            return ImagePreviewVO.builder()
                    .id(attachmentId)
                    .url(minioUtil.preview(fullPath))
                    .build();
        } else {
            return ImagePreviewVO.builder().id("xxx").url("xxx").build();
        }
    }

    @Override
    public String preview() {
        return minioUtil.preview("hrims/away.jpg");
    }

    @Override
    public Boolean remove(String attachId) {
        Attachment attachment = attachmentMapper.selectById(attachId);
        if (attachment != null) {
            return minioUtil.remove(attachment.getFullPath());
        }
        return true;
    }
}
