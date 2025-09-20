package com.gosaint.service.permissionManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.permissionManagement.Permissions;
import java.util.List;

public interface PermissionsService {
    // 创建权限
    int createPermission(Permissions permission);

    // 根据ID查询权限
    Permissions getPermissionById(String permissionId);

    // 根据名称查询权限
    Permissions getPermissionByName(String permissionName);

    // 查询所有权限
    PageInfo<Permissions> getAllPermissionsByPage(int pageNum, int pageSize);

    // 更新权限
    int updatePermission(Permissions permission);

    // 删除权限
    int deletePermission(String permissionId);
}