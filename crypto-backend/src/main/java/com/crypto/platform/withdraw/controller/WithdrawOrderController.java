package com.crypto.platform.withdraw.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.withdraw.entity.WithdrawOrder;
import com.crypto.platform.withdraw.service.IWithdrawOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 提现订单管理接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "提现管理")
@RestController
@RequestMapping("/admin/withdraw")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class WithdrawOrderController {

    private final IWithdrawOrderService withdrawOrderService;

    @Operation(summary = "提现订单列表")
    @GetMapping("/list")
    public Result<Page<WithdrawOrder>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            WithdrawOrder query) {
        Page<WithdrawOrder> pageParam = new Page<>(page, size);
        Page<WithdrawOrder> result = withdrawOrderService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "提现订单详情")
    @GetMapping("/{id}")
    public Result<WithdrawOrder> detail(@PathVariable Long id) {
        WithdrawOrder order = withdrawOrderService.getById(id);
        return Result.success(order);
    }

    @Operation(summary = "审核通过")
    @PutMapping("/{id}/approve")
    public Result<Void> approve(@PathVariable Long id) {
        // TODO: 从当前登录用户获取 auditorId
        Long auditorId = 1L;
        boolean success = withdrawOrderService.approve(id, auditorId);
        return success ? Result.success() : Result.error("审核失败");
    }

    @Operation(summary = "审核拒绝")
    @PutMapping("/{id}/reject")
    public Result<Void> reject(@PathVariable Long id, @RequestParam String reason) {
        // TODO: 从当前登录用户获取 auditorId
        Long auditorId = 1L;
        boolean success = withdrawOrderService.reject(id, auditorId, reason);
        return success ? Result.error("审核失败") : Result.success();
    }
}

