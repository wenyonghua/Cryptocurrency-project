package com.crypto.platform.api.controller;

import cn.hutool.core.util.RandomUtil;
import com.crypto.platform.admin.entity.SysUser;
import com.crypto.platform.admin.service.ISysUserService;
import com.crypto.platform.common.result.Result;
import com.crypto.platform.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 * 
 * @author crypto-platform
 * @since 2025-12-30
 */
@Tag(name = "认证接口", description = "用户登录、注册等认证相关接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final ISysUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "用户登录", description = "用户名密码登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        try {
            // 认证
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // 获取用户信息
            SysUser user = userService.getUserByUsername(request.getUsername());
            
            // 生成 Token
            String token = tokenProvider.generateToken(user.getUsername(), user.getId());

            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("userInfo", user);

            return Result.success("登录成功", data);
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }
    }

    @Operation(summary = "用户注册", description = "新用户注册")
    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody RegisterRequest request) {
        // 检查用户名是否存在
        if (userService.getUserByUsername(request.getUsername()) != null) {
            return Result.error("用户名已存在");
        }

        // 检查邮箱是否存在
        if (request.getEmail() != null && userService.getUserByEmail(request.getEmail()) != null) {
            return Result.error("邮箱已被注册");
        }

        // 创建用户
        SysUser user = new SysUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setNickname(request.getUsername());
        user.setStatus(1);
        user.setUserType(0);
        user.setKycLevel(0);
        user.setVipLevel(0);
        
        // 生成 UID（8位数字）
        user.setUid("UID" + RandomUtil.randomNumbers(7));
        
        // 生成邀请码（6位字母数字）
        user.setInviteCode(RandomUtil.randomString(6).toUpperCase());
        
        boolean success = userService.save(user);
        if (success) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");
    }

    @Operation(summary = "获取当前用户信息", description = "根据Token获取当前登录用户信息")
    @GetMapping("/userinfo")
    public Result<SysUser> getUserInfo() {
        // 从 SecurityContext 中获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Result.error("未登录");
        }
        
        String username = authentication.getName();
        SysUser user = userService.getUserByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }
        
        return Result.success(user);
    }

    /**
     * 登录请求参数
     */
    @Data
    static class LoginRequest {
        @NotBlank(message = "用户名不能为空")
        private String username;
        
        @NotBlank(message = "密码不能为空")
        private String password;
    }

    /**
     * 注册请求参数
     */
    @Data
    static class RegisterRequest {
        @NotBlank(message = "用户名不能为空")
        private String username;
        
        @NotBlank(message = "密码不能为空")
        private String password;
        
        private String email;
    }
}
