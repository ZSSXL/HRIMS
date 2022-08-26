package com.zss.hrims.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/8/15 16:07
 * @desc 关系对应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationBO implements Serializable {

    private String id;

    private String name;

    private String relationId;
}
