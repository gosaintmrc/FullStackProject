package com.gosaint.service.permissionManagement;

import java.util.List;

public interface UserRoleService {
    // 为用户添加角色
    int addRoleToUser(String userId, String roleId);

    // 根据用户ID获取角色列表
    List<String> getRolesByUserId(String userId);

    // 根据角色ID获取用户列表
    List<String> getUsersByRoleId(String roleId);

    // 解除用户-角色关联
    int removeRoleFromUser(String userId, String roleId);

    // 解除用户的所有角色关联
    int removeAllRolesFromUser(String userId);
}