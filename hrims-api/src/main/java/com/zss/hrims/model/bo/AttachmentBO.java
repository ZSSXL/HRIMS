package com.zss.hrims.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/8/24 13:27
 * @desc 附件信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentBO  implements Serializable {

    private String id;

    private String fullPath;
}
