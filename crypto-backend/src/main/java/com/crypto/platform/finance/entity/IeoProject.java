package com.crypto.platform.finance.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ieo_project")
public class IeoProject implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;
    private String currency;
    private BigDecimal totalAmount;
    private BigDecimal price;
    private BigDecimal minBuy;
    private BigDecimal maxBuy;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

