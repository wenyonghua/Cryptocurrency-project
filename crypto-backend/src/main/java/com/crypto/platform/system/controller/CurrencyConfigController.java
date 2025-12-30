package com.crypto.platform.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.system.entity.CurrencyConfig;
import com.crypto.platform.system.service.ICurrencyConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "币种配置管理")
@RestController
@RequestMapping("/admin/currency-config")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CurrencyConfigController {
    private final ICurrencyConfigService currencyConfigService;

    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public Result<Page<CurrencyConfig>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           CurrencyConfig query) {
        return Result.success(currencyConfigService.pageQuery(new Page<>(page, size), query));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public Result<CurrencyConfig> detail(@PathVariable Long id) {
        return Result.success(currencyConfigService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    public Result<Void> add(@RequestBody CurrencyConfig entity) {
        currencyConfigService.save(entity);
        return Result.success();
    }

    @Operation(summary = "更新")
    @PutMapping
    public Result<Void> update(@RequestBody CurrencyConfig entity) {
        currencyConfigService.updateById(entity);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        currencyConfigService.removeById(id);
        return Result.success();
    }
}
