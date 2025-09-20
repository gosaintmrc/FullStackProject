package com.gosaint.service.permissionManagement;

import java.util.List;

public interface RolePermissionService {
    // 为角色添加权限
    int addPermissionToRole(String roleId, String permissionId);

    // 根据角色ID获取权限列表
    List<String> getPermissionsByRoleId(String roleId);

    // 根据权限ID获取角色列表
    List<String> getRolesByPermissionId(String permissionId);

    // 解除角色-权限关联
    int removePermissionFromRole(String roleId, String permissionId);

    // 解除角色的所有权限关联
    int removeAllPermissionsFromRole(String roleId);
}