package com.crypto.platform.deposit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.deposit.entity.DepositOrder;
import com.crypto.platform.deposit.service.IDepositOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 充值订单管理接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "充值管理")
@RestController
@RequestMapping("/admin/deposit")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class DepositOrderController {

    private final IDepositOrderService depositOrderService;

    @Operation(summary = "充值订单列表")
    @GetMapping("/list")
    public Result<Page<DepositOrder>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            DepositOrder query) {
        Page<DepositOrder> pageParam = new Page<>(page, size);
        Page<DepositOrder> result = depositOrderService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "充值订单详情")
    @GetMapping("/{id}")
    public Result<DepositOrder> detail(@PathVariable Long id) {
        DepositOrder order = depositOrderService.getById(id);
        return Result.success(order);
    }

    @Operation(summary = "确认充值")
    @PutMapping("/{id}/confirm")
    public Result<Void> confirm(@PathVariable Long id) {
        boolean success = depositOrderService.confirmDeposit(id);
        return success ? Result.success() : Result.error("确认失败");
    }
}

