package com.crypto.platform.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 玩家详情 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
public class PlayerDetailDTO {
    
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
     * 头像
     */
    private String avatar;
    
    /**
     * VIP等级
     */
    private Integer vipLevel;
    
    /**
     * KYC等级
     */
    private Integer kycLevel;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
     * 真实姓名
     */
    private String realName;
    
    /**
     * 邀请码
     */
    private String inviteCode;
    
    /**
     * 邀请人ID
     */
    private Long inviterId;
    
    /**
     * 邀请人用户名
     */
    private String inviterUsername;
    
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
    
    /**
     * 资产列表
     */
    private List<AssetDTO> assets;
    
    /**
     * 统计数据
     */
    private StatisticsDTO statistics;
    
    /**
     * 资产 DTO
     */
    @Data
    public static class AssetDTO {
        /**
         * 币种
         */
        private String currency;
        
        /**
         * 可用余额
         */
        private BigDecimal available;
        
        /**
         * 冻结余额
         */
        private BigDecimal frozen;
        
        /**
         * 总余额
         */
        private BigDecimal total;
    }
    
    /**
     * 统计数据 DTO
     */
    @Data
    public static class StatisticsDTO {
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
    }
}

