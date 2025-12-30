package com.crypto.platform.finance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.finance.entity.MiningConfig;
import com.crypto.platform.finance.service.IMiningConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "挖矿配置管理")
@RestController
@RequestMapping("/admin/mining-config")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class MiningConfigController {
    private final IMiningConfigService miningConfigService;

    @Operation(summary = "挖矿配置列表")
    @GetMapping("/list")
    public Result<Page<MiningConfig>> list(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            MiningConfig query) {
        return Result.success(miningConfigService.pageQuery(new Page<>(page, size), query));
    }

    @Operation(summary = "配置详情")
    @GetMapping("/{id}")
    public Result<MiningConfig> detail(@PathVariable Long id) {
        return Result.success(miningConfigService.getById(id));
    }

    @Operation(summary = "新增配置")
    @PostMapping
    public Result<Void> add(@RequestBody MiningConfig config) {
        miningConfigService.save(config);
        return Result.success();
    }

    @Operation(summary = "更新配置")
    @PutMapping
    public Result<Void> update(@RequestBody MiningConfig config) {
        miningConfigService.updateById(config);
        return Result.success();
    }

    @Operation(summary = "删除配置")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        miningConfigService.removeById(id);
        return Result.success();
    }
}

