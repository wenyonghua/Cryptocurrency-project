package com.crypto.platform.finance.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.finance.entity.IeoProject;
import com.crypto.platform.finance.service.IIeoProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "IEO项目管理")
@RestController
@RequestMapping("/admin/ieo-project")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class IeoProjectController {
    private final IIeoProjectService ieoProjectService;

    @Operation(summary = "列表查询")
    @GetMapping("/list")
    public Result<Page<IeoProject>> list(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           IeoProject query) {
        return Result.success(ieoProjectService.pageQuery(new Page<>(page, size), query));
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public Result<IeoProject> detail(@PathVariable Long id) {
        return Result.success(ieoProjectService.getById(id));
    }

    @Operation(summary = "新增")
    @PostMapping
    public Result<Void> add(@RequestBody IeoProject entity) {
        ieoProjectService.save(entity);
        return Result.success();
    }

    @Operation(summary = "更新")
    @PutMapping
    public Result<Void> update(@RequestBody IeoProject entity) {
        ieoProjectService.updateById(entity);
        return Result.success();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        ieoProjectService.removeById(id);
        return Result.success();
    }
}
