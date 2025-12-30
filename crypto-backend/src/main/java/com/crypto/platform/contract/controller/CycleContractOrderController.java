package com.crypto.platform.contract.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.contract.entity.CycleContractOrder;
import com.crypto.platform.contract.service.ICycleContractOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "周期合约管理")
@RestController
@RequestMapping("/admin/cycle-contract")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CycleContractOrderController {

    private final ICycleContractOrderService cycleContractOrderService;

    @Operation(summary = "周期合约订单列表")
    @GetMapping("/list")
    public Result<Page<CycleContractOrder>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            CycleContractOrder query) {
        Page<CycleContractOrder> pageParam = new Page<>(page, size);
        Page<CycleContractOrder> result = cycleContractOrderService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "订单详情")
    @GetMapping("/{id}")
    public Result<CycleContractOrder> detail(@PathVariable Long id) {
        CycleContractOrder order = cycleContractOrderService.getById(id);
        return Result.success(order);
    }
}

