package com.crypto.platform.finance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.finance.entity.FinanceProduct;
import com.crypto.platform.finance.service.IFinanceProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "理财产品管理")
@RestController
@RequestMapping("/admin/finance-product")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class FinanceProductController {

    private final IFinanceProductService financeProductService;

    @Operation(summary = "理财产品列表")
    @GetMapping("/list")
    public Result<Page<FinanceProduct>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            FinanceProduct query) {
        Page<FinanceProduct> pageParam = new Page<>(page, size);
        Page<FinanceProduct> result = financeProductService.pageQuery(pageParam, query);
        return Result.success(result);
    }

    @Operation(summary = "产品详情")
    @GetMapping("/{id}")
    public Result<FinanceProduct> detail(@PathVariable Long id) {
        FinanceProduct product = financeProductService.getById(id);
        return Result.success(product);
    }

    @Operation(summary = "新增产品")
    @PostMapping
    public Result<Void> add(@RequestBody FinanceProduct product) {
        financeProductService.save(product);
        return Result.success("新增成功");
    }

    @Operation(summary = "更新产品")
    @PutMapping
    public Result<Void> update(@RequestBody FinanceProduct product) {
        financeProductService.updateById(product);
        return Result.success("更新成功");
    }

    @Operation(summary = "删除产品")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        financeProductService.removeById(id);
        return Result.success("删除成功");
    }
}

