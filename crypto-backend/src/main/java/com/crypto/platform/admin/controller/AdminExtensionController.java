package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.mapper.SysUserMapper;
import com.crypto.platform.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * 管理后台扩展接口（登录日志、银行卡等）
 */
@Tag(name = "管理后台扩展")
@RestController
@RequestMapping("/admin/extension")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminExtensionController {

    private final SysUserMapper userMapper;

    public AdminExtensionController(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Operation(summary = "登录日志")
    @GetMapping("/login-logs")
    public Result<Page<SysUser>> loginLogs(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(SysUser::getLoginTime)
                .orderByDesc(SysUser::getLoginTime);
        return Result.success(userMapper.selectPage(new Page<>(page, size), wrapper));
    }

    @Operation(summary = "银行卡列表")
    @GetMapping("/user-banks")
    public Result<Page<UserBankDTO>> userBanks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<UserBankDTO> empty = new Page<>(page, size);
        empty.setRecords(Collections.emptyList());
        empty.setTotal(0);
        return Result.success(empty);
    }

    public static class UserBankDTO {
        private Long id;
        private Long userId;
        private String bankName;
        private String cardNo;
        private String holderName;
        private Integer status;
        private LocalDateTime createTime;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getBankName() { return bankName; }
        public void setBankName(String bankName) { this.bankName = bankName; }
        public String getCardNo() { return cardNo; }
        public void setCardNo(String cardNo) { this.cardNo = cardNo; }
        public String getHolderName() { return holderName; }
        public void setHolderName(String holderName) { this.holderName = holderName; }
        public Integer getStatus() { return status; }
        public void setStatus(Integer status) { this.status = status; }
        public LocalDateTime getCreateTime() { return createTime; }
        public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    }
}
