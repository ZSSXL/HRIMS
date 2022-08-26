package com.zss.hrims.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/29 17:11
 * @desc 候选人简略信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSimpleBO implements Serializable {

    private String id;

    private String name;

    private String phone;

    private String post;

    private String graduationTime;

    private String enterprise;

    private String rating;

    private Integer state;
}
