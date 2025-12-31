package com.crypto.platform.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 资产调整 DTO
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Data
@Schema(description = "资产调整请求")
public class AssetAdjustmentDTO {
    
    @Schema(description = "用户ID", example = "1")
    private Long userId;
    
    @Schema(description = "币种代码", example = "USDT")
    private String currency;
    
    @Schema(description = "调整金额（正数为增加，负数为扣除）", example = "100.00")
    private BigDecimal amount;
    
    @Schema(description = "调整类型：1-充值，2-扣除", example = "1")
    private Integer adjustType;
    
    @Schema(description = "备注", example = "管理员手动调整")
    private String remark;
}

