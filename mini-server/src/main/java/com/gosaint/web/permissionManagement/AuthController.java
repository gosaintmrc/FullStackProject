package com.gosaint.web.permissionManagement;

import com.gosaint.model.permissionManagement.Users;
import com.gosaint.service.permissionManagement.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@Tag(name = "认证管理", description = "用户认证相关的API接口")
public class AuthController {

    @Autowired
    UsersService usersService;


    @Operation(summary = "用户登录", description = "用户登录系统获取JWT令牌")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return null;
    }

    @Operation(summary = "用户注册", description = "注册新用户到系统")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // 检查用户名是否已存在
        if (usersService.getUserByUsername(registerRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("用户名已存在");
        }

        // 创建新用户
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setIsActive(true);
        user.setCreatedAt(OffsetDateTime.now());

        usersService.createUser(user);

        return ResponseEntity.ok("用户注册成功");
    }

    // 登录请求DTO
    public static class LoginRequest {
        private String username;
        private String password;

        // getter和setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的详细信息")
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {

        return null;
    }

    // 注册请求DTO
    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;
        private String fullName;

        // getter和setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}