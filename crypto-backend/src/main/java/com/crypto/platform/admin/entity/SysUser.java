package com.crypto.platform.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户实体
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码（BCrypt加密）
     */
    @JsonIgnore
    private String password;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 头像URL
     */
    private String avatar;
    
    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;
    
    /**
     * 用户类型：0-普通用户，1-管理员
     */
    private Integer userType;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 身份证号
     */
    @JsonIgnore
    private String idCard;
    
    /**
     * KYC等级：0-未认证，1-初级，2-高级
     */
    private Integer kycLevel;
    
    /**
     * VIP等级
     */
    private Integer vipLevel;
    
    /**
     * 用户UID
     */
    private String uid;
    
    /**
     * 邀请码
     */
    private String inviteCode;
    
    /**
     * 邀请人ID
     */
    private Long inviterId;
    
    /**
     * 最后登录IP
     */
    private String loginIp;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime loginTime;
    
    /**
     * 逻辑删除：0-未删除，1-已删除
     */
    @TableLogic
    private Integer deleted;
    
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

