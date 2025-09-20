package com.gosaint.service.permissionManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.permissionManagement.Roles;
import java.util.List;

public interface RolesService {
    // 创建角色
    int createRole(Roles role);

    // 根据ID查询角色
    Roles getRoleById(String roleId);

    // 根据名称查询角色
    Roles getRoleByName(String roleName);

    // 查询所有角色
    List<Roles> getAllRoles();

    PageInfo<Roles> getAllRolesByPage(int pageNum,int pageSize);

    // 更新角色
    int updateRole(Roles role);

    // 删除角色
    int deleteRole(String roleId);
}