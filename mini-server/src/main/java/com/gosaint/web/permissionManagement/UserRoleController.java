package com.gosaint.web.permissionManagement;

import com.gosaint.service.permissionManagement.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@Tag(name = "用户-角色管理", description = "用户与角色关联相关的API接口")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @Operation(summary = "为用户分配角色", description = "将角色分配给指定用户")
    @PostMapping
    public ResponseEntity<Integer> addRoleToUser(
            @Parameter(description = "用户ID") @RequestParam String userId,
            @Parameter(description = "角色ID") @RequestParam String roleId) {
        int result = userRoleService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "获取用户的角色列表", description = "查询指定用户拥有的所有角色ID列表")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<String>> getRolesByUserId(
            @Parameter(description = "用户ID") @PathVariable String userId) {
        List<String> roles = userRoleService.getRolesByUserId(userId);
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "获取角色的用户列表", description = "查询拥有指定角色的所有用户ID列表")
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<String>> getUsersByRoleId(
            @Parameter(description = "角色ID") @PathVariable String roleId) {
        List<String> users = userRoleService.getUsersByRoleId(roleId);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "解除用户的角色", description = "移除用户的指定角色")
    @DeleteMapping
    public ResponseEntity<Integer> removeRoleFromUser(
            @Parameter(description = "用户ID") @RequestParam String userId,
            @Parameter(description = "角色ID") @RequestParam String roleId) {
        int result = userRoleService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "解除用户的所有角色", description = "移除用户的所有角色关联")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Integer> removeAllRolesFromUser(
            @Parameter(description = "用户ID") @PathVariable String userId) {
        int result = userRoleService.removeAllRolesFromUser(userId);
        return ResponseEntity.ok(result);
    }
}