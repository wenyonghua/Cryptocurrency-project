package com.crypto.platform.trade.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.trade.entity.SpotOrder;
import com.crypto.platform.trade.service.ISpotOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 现货交易订单管理接口
 *
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "现货交易管理")
@RestController
@RequestMapping("/admin/spot")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class SpotOrderController {

    private final ISpotOrderService spotOrderService;

    @Operation(summary = "现货订单列表")
    @GetMapping("/list")
    public Result<Page<SpotOrder>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            SpotOrder query) {
        Page<SpotOrder> pageParam = new Page<>(page, size);
        Page<SpotOrder> result = spotOrderService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "订单详情")
    @GetMapping("/{id}")
    public Result<SpotOrder> detail(@PathVariable Long id) {
        SpotOrder order = spotOrderService.getById(id);
        return Result.success(order);
    }

    @Operation(summary = "撤销订单")
    @PostMapping("/cancel/{orderNo}")
    public Result<Void> cancel(@PathVariable String orderNo) {
        spotOrderService.cancelOrder(orderNo);
        return Result.success("撤销成功");
    }
}

