package com.crypto.platform.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.system.entity.TradingPair;
import com.crypto.platform.system.service.ITradingPairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "交易对配置管理")
@RestController
@RequestMapping("/admin/trading-pair")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class TradingPairController {
    private final ITradingPairService tradingPairService;

    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public Result<Page<TradingPair>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           TradingPair query) {
        return Result.success(tradingPairService.pageQuery(new Page<>(page, size), query));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public Result<TradingPair> detail(@PathVariable Long id) {
        return Result.success(tradingPairService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    public Result<Void> add(@RequestBody TradingPair entity) {
        tradingPairService.save(entity);
        return Result.success();
    }

    @Operation(summary = "更新")
    @PutMapping
    public Result<Void> update(@RequestBody TradingPair entity) {
        tradingPairService.updateById(entity);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        tradingPairService.removeById(id);
        return Result.success();
    }
}
