package com.zss.hrims.service;

import com.zss.hrims.model.vo.ImagePreviewVO;

import java.io.InputStream;

/**
 * @author ZSS
 * @date 2022/8/23 16:26
 * @desc 附件服务层接口
 */
public interface AttachmentService {

    /**
     * 上传文件
     *
     * @param inputStream 文件输入流
     * @param fileName    文件名
     * @param fileSize    文件大小
     * @param key         目标文件夹
     * @param contentType 文件类型
     * @return 上传结果
     */
    ImagePreviewVO upload(InputStream inputStream, String fileName, long fileSize, String key, String contentType);

    /**
     * 获取预览地址
     *
     * @return url
     */
    String preview();

    /**
     * 删除文件
     *
     * @param attachId 附件Id
     * @return 是否删除文件
     */
    Boolean remove(String attachId);
}
