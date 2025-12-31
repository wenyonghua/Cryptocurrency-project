package com.crypto.platform.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.contract.entity.CycleContractOrder;
import com.crypto.platform.contract.entity.SecondsContractOrder;
import com.crypto.platform.contract.service.ICycleContractOrderService;
import com.crypto.platform.contract.service.ISecondsContractOrderService;
import com.crypto.platform.finance.entity.IeoProject;
import com.crypto.platform.finance.service.IIeoProjectService;
import com.crypto.platform.trade.entity.SpotOrder;
import com.crypto.platform.trade.service.ISpotOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理 - 杂项管理控制器
 * 
 * @author crypto-platform
 * @since 2025-12-31
 */
@Tag(name = "后台管理 - 杂项管理", description = "秒合约、周期合约、IEO、现货交易等管理接口")
@RestController
@RequestMapping("/admin/misc")
@RequiredArgsConstructor
public class MiscManagementController {
    
    private final ISecondsContractOrderService secondsContractOrderService;
    private final ICycleContractOrderService cycleContractOrderService;
    private final IIeoProjectService ieoProjectService;
    private final ISpotOrderService spotOrderService;
    
    // ==================== 秒合约管理 ====================
    
    @Operation(summary = "获取秒合约订单列表", description = "分页查询秒合约订单，支持多条件筛选")
    @GetMapping("/seconds-contract/orders")
    public Result<Page<SecondsContractOrder>> getSecondsContractOrders(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,
            
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,
            
            @Parameter(description = "订单号")
            @RequestParam(required = false) String orderNo,
            
            @Parameter(description = "用户ID")
            @RequestParam(required = false) Long userId,
            
            @Parameter(description = "交易对")
            @RequestParam(required = false) String symbol,
            
            @Parameter(description = "状态：0-进行中，1-盈利，2-亏损，3-平局")
            @RequestParam(required = false) Integer status
    ) {
        Page<SecondsContractOrder> page = new Page<>(current, size);
        SecondsContractOrder query = new SecondsContractOrder();
        query.setOrderNo(orderNo);
        query.setUserId(userId);
        query.setSymbol(symbol);
        query.setStatus(status);
        
        Page<SecondsContractOrder> result = secondsContractOrderService.pageQuery(page, query);
        return Result.success(result);
    }
    
    @Operation(summary = "获取秒合约订单详情", description = "根据订单ID获取秒合约订单详情")
    @GetMapping("/seconds-contract/orders/{id}")
    public Result<SecondsContractOrder> getSecondsContractOrderDetail(
            @Parameter(description = "订单ID", example = "1")
            @PathVariable Long id
    ) {
        SecondsContractOrder order = secondsContractOrderService.getById(id);
        return Result.success(order);
    }
    
    // ==================== 周期合约管理 ====================
    
    @Operation(summary = "获取周期合约订单列表", description = "分页查询周期合约订单，支持多条件筛选")
    @GetMapping("/cycle-contract/orders")
    public Result<Page<CycleContractOrder>> getCycleContractOrders(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,
            
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,
            
            @Parameter(description = "订单号")
            @RequestParam(required = false) String orderNo,
            
            @Parameter(description = "用户ID")
            @RequestParam(required = false) Long userId,
            
            @Parameter(description = "交易对")
            @RequestParam(required = false) String symbol,
            
            @Parameter(description = "状态：0-进行中，1-盈利，2-亏损，3-平局")
            @RequestParam(required = false) Integer status
    ) {
        Page<CycleContractOrder> page = new Page<>(current, size);
        CycleContractOrder query = new CycleContractOrder();
        query.setOrderNo(orderNo);
        query.setUserId(userId);
        query.setSymbol(symbol);
        query.setStatus(status);
        
        Page<CycleContractOrder> result = cycleContractOrderService.pageQuery(page, query);
        return Result.success(result);
    }
    
    @Operation(summary = "获取周期合约订单详情", description = "根据订单ID获取周期合约订单详情")
    @GetMapping("/cycle-contract/orders/{id}")
    public Result<CycleContractOrder> getCycleContractOrderDetail(
            @Parameter(description = "订单ID", example = "1")
            @PathVariable Long id
    ) {
        CycleContractOrder order = cycleContractOrderService.getById(id);
        return Result.success(order);
    }
    
    // ==================== IEO项目管理 ====================
    
    @Operation(summary = "获取IEO项目列表", description = "分页查询IEO项目列表")
    @GetMapping("/ieo/projects")
    public Result<Page<IeoProject>> getIeoProjects(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,
            
            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,
            
            @Parameter(description = "状态：0-未开始，1-进行中，2-已结束")
            @RequestParam(required = false) Integer status
    ) {
        Page<IeoProject> page = new Page<>(current, size);
        IeoProject query = new IeoProject();
        query.setStatus(status);
        
        Page<IeoProject> result = ieoProjectService.pageQuery(page, query);
        return Result.success(result);
    }

    @Operation(summary = "获取IEO项目详情", description = "根据项目ID获取IEO项目详情")
    @GetMapping("/ieo/projects/{id}")
    public Result<IeoProject> getIeoProjectDetail(
            @Parameter(description = "项目ID", example = "1")
            @PathVariable Long id
    ) {
        IeoProject project = ieoProjectService.getById(id);
        return Result.success(project);
    }

    @Operation(summary = "创建IEO项目", description = "创建新的IEO项目")
    @PostMapping("/ieo/projects")
    public Result<Boolean> createIeoProject(@RequestBody IeoProject project) {
        boolean success = ieoProjectService.save(project);
        return Result.success(success);
    }

    @Operation(summary = "更新IEO项目", description = "更新IEO项目信息")
    @PutMapping("/ieo/projects/{id}")
    public Result<Boolean> updateIeoProject(
            @Parameter(description = "项目ID", example = "1")
            @PathVariable Long id,
            @RequestBody IeoProject project
    ) {
        project.setId(id);
        boolean success = ieoProjectService.updateById(project);
        return Result.success(success);
    }

    @Operation(summary = "删除IEO项目", description = "删除IEO项目")
    @DeleteMapping("/ieo/projects/{id}")
    public Result<Boolean> deleteIeoProject(
            @Parameter(description = "项目ID", example = "1")
            @PathVariable Long id
    ) {
        boolean success = ieoProjectService.removeById(id);
        return Result.success(success);
    }

    // ==================== 现货交易管理 ====================

    @Operation(summary = "获取现货交易订单列表", description = "分页查询现货交易订单，支持多条件筛选")
    @GetMapping("/spot/orders")
    public Result<Page<SpotOrder>> getSpotOrders(
            @Parameter(description = "当前页", example = "1")
            @RequestParam(defaultValue = "1") Long current,

            @Parameter(description = "每页大小", example = "10")
            @RequestParam(defaultValue = "10") Long size,

            @Parameter(description = "订单号")
            @RequestParam(required = false) String orderNo,

            @Parameter(description = "用户ID")
            @RequestParam(required = false) Long userId,

            @Parameter(description = "交易对")
            @RequestParam(required = false) String symbol,

            @Parameter(description = "状态：0-待成交，1-部分成交，2-完全成交，3-已撤销，4-已过期")
            @RequestParam(required = false) Integer status
    ) {
        Page<SpotOrder> page = new Page<>(current, size);
        SpotOrder query = new SpotOrder();
        query.setOrderNo(orderNo);
        query.setUserId(userId);
        query.setSymbol(symbol);
        query.setStatus(status);

        Page<SpotOrder> result = spotOrderService.pageQuery(page, query);
        return Result.success(result);
    }

    @Operation(summary = "获取现货交易订单详情", description = "根据订单ID获取现货交易订单详情")
    @GetMapping("/spot/orders/{id}")
    public Result<SpotOrder> getSpotOrderDetail(
            @Parameter(description = "订单ID", example = "1")
            @PathVariable Long id
    ) {
        SpotOrder order = spotOrderService.getById(id);
        return Result.success(order);
    }
}
