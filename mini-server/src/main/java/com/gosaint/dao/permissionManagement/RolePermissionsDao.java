package com.gosaint.dao.permissionManagement;

import com.gosaint.model.permissionManagement.RolePermissions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionsDao {
    // 为角色添加权限
    int insert(RolePermissions rolePermissions);

    // 根据角色ID查询权限列表
    List<String> selectPermissionsByRoleId(@Param("roleId") String roleId);

    // 根据权限ID查询角色列表
    List<String> selectRolesByPermissionId(@Param("permissionId") String permissionId);

    // 解除角色-权限关联
    int delete(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    // 解除角色的所有权限关联
    int deleteByRoleId(@Param("roleId") String roleId);
}