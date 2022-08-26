package com.zss.hrims.model.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zss.hrims.model.bo.CandidateSimpleBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ZSS
 * @date 2022/8/1 17:24
 * @desc 候选人查询记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRecordVO implements Serializable {

    @ApiModelProperty(value = "候选人基本信息")
    private IPage<CandidateSimpleBO> candidateSimplePage;

    @ApiModelProperty(value = "状态统计")
    private Map<String, Integer> stateCount;
}
