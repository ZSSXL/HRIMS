package com.zss.hrims.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * @author ZSS
 * @date 2022/7/22 15:04
 * @desc 系统用户实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hri_system_user")
public class SystemUser extends Model<SystemUser> implements Serializable {

    /**
     * 账户id
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户权限
     */
    private Integer role;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private String createTime;
}
