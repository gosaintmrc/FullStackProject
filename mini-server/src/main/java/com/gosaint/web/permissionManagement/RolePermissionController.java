package com.gosaint.web.permissionManagement;

import com.gosaint.service.permissionManagement.RolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
@Tag(name = "角色-权限管理", description = "角色与权限关联相关的API接口")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Operation(summary = "为角色分配权限", description = "将权限分配给指定角色")
    @PostMapping
    public ResponseEntity<Integer> addPermissionToRole(
            @Parameter(description = "角色ID") @RequestParam String roleId,
            @Parameter(description = "权限ID") @RequestParam String permissionId) {
        int result = rolePermissionService.addPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "获取角色的权限列表", description = "查询指定角色拥有的所有权限ID列表")
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<String>> getPermissionsByRoleId(
            @Parameter(description = "角色ID") @PathVariable String roleId) {
        List<String> permissions = rolePermissionService.getPermissionsByRoleId(roleId);
        return ResponseEntity.ok(permissions);
    }

    @Operation(summary = "获取权限的角色列表", description = "查询拥有指定权限的所有角色ID列表")
    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<List<String>> getRolesByPermissionId(
            @Parameter(description = "权限ID") @PathVariable String permissionId) {
        List<String> roles = rolePermissionService.getRolesByPermissionId(permissionId);
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "解除角色的权限", description = "移除角色的指定权限")
    @DeleteMapping
    public ResponseEntity<Integer> removePermissionFromRole(
            @Parameter(description = "角色ID") @RequestParam String roleId,
            @Parameter(description = "权限ID") @RequestParam String permissionId) {
        int result = rolePermissionService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "解除角色的所有权限", description = "移除角色的所有权限关联")
    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Integer> removeAllPermissionsFromRole(
            @Parameter(description = "角色ID") @PathVariable String roleId) {
        int result = rolePermissionService.removeAllPermissionsFromRole(roleId);
        return ResponseEntity.ok(result);
    }
}