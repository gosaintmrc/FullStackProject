package com.gosaint.web.permissionManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.permissionManagement.Roles;
import com.gosaint.service.permissionManagement.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "角色管理", description = "角色相关的API接口")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @Operation(summary = "创建角色", description = "添加新角色到系统")
    @PostMapping
    public ResponseEntity<Integer> createRole(@RequestBody Roles role) {
        int result = rolesService.createRole(role);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "根据ID获取角色", description = "通过角色ID查询角色详情")
    @GetMapping("/{roleId}")
    public ResponseEntity<Roles> getRoleById(@Parameter(description = "角色ID") @PathVariable String roleId) {
        Roles role = rolesService.getRoleById(roleId);
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "分页获取所有角色", description = "分页查询系统中的所有角色列表")
    @GetMapping
    public ResponseEntity<PageInfo<Roles>> getAllRolesByPages(int pageNum, int pageSize) {
        return ResponseEntity.ok(rolesService.getAllRolesByPage(pageNum,pageSize));
    }

    @Operation(summary = "获取所有角色", description = "查询系统中的所有角色列表")
    @GetMapping("/all")
    public ResponseEntity<List<Roles>> getAllRoles() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    @Operation(summary = "更新角色", description = "修改角色信息")
    @PutMapping
    public ResponseEntity<Integer> updateRole(@RequestBody Roles role) {
        int result = rolesService.updateRole(role);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "删除角色", description = "根据角色ID删除角色")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Integer> deleteRole(@Parameter(description = "角色ID") @PathVariable String roleId) {
        int result = rolesService.deleteRole(roleId);
        return ResponseEntity.ok(result);
    }
}