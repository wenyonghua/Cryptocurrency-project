package com.crypto.platform.contract.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.contract.entity.SecondsContractOrder;
import com.crypto.platform.contract.service.ISecondsContractOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "秒合约管理")
@RestController
@RequestMapping("/admin/seconds-contract")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class SecondsContractOrderController {

    private final ISecondsContractOrderService secondsContractOrderService;

    @Operation(summary = "秒合约订单列表")
    @GetMapping("/list")
    public Result<Page<SecondsContractOrder>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            SecondsContractOrder query) {
        Page<SecondsContractOrder> pageParam = new Page<>(page, size);
        Page<SecondsContractOrder> result = secondsContractOrderService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "订单详情")
    @GetMapping("/{id}")
    public Result<SecondsContractOrder> detail(@PathVariable Long id) {
        SecondsContractOrder order = secondsContractOrderService.getById(id);
        return Result.success(order);
    }
}

