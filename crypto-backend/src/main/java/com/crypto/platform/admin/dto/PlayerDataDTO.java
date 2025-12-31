package com.crypto.platform.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 玩家数据 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class PlayerDataDTO {
    
    /**
     * 用户ID
     */
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 用户UID
     */
    private String uid;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * VIP等级
     */
    private Integer vipLevel;
    
    /**
     * KYC等级
     */
    private Integer kycLevel;
    
    /**
     * 状态：0-禁用，1-正常
     */
    private Integer status;
    
    /**
     * 总资产（USDT）
     */
    private BigDecimal totalAsset;
    
    /**
     * 累计充值
     */
    private BigDecimal totalDeposit;
    
    /**
     * 累计提现
     */
    private BigDecimal totalWithdraw;
    
    /**
     * 累计交易量
     */
    private BigDecimal totalTrade;
    
    /**
     * 邀请人数
     */
    private Integer inviteCount;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    
    /**
     * 注册时间
     */
    private LocalDateTime createTime;
}

