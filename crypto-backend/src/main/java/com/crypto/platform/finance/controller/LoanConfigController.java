package com.crypto.platform.finance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.finance.entity.LoanConfig;
import com.crypto.platform.finance.service.ILoanConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "借贷配置管理")
@RestController
@RequestMapping("/admin/loan-config")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class LoanConfigController {
    private final ILoanConfigService loanConfigService;

    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public Result<Page<LoanConfig>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           LoanConfig query) {
        return Result.success(loanConfigService.pageQuery(new Page<>(page, size), query));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public Result<LoanConfig> detail(@PathVariable Long id) {
        return Result.success(loanConfigService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    public Result<Void> add(@RequestBody LoanConfig entity) {
        loanConfigService.save(entity);
        return Result.success();
    }

    @Operation(summary = "更新")
    @PutMapping
    public Result<Void> update(@RequestBody LoanConfig entity) {
        loanConfigService.updateById(entity);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        loanConfigService.removeById(id);
        return Result.success();
    }
}
