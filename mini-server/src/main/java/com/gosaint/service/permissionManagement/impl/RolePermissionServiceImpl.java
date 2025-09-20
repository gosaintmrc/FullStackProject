package com.gosaint.service.permissionManagement.impl;

import com.gosaint.dao.permissionManagement.RolePermissionsDao;
import com.gosaint.model.permissionManagement.RolePermissions;
import com.gosaint.service.permissionManagement.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionsDao rolePermissionsDao;

    @Override
    public int addPermissionToRole(String roleId, String permissionId) {
        RolePermissions rolePermissions = new RolePermissions();
        rolePermissions.setRoleId(roleId);
        rolePermissions.setPermissionId(permissionId);
        return rolePermissionsDao.insert(rolePermissions);
    }

    @Override
    public List<String> getPermissionsByRoleId(String roleId) {
        return rolePermissionsDao.selectPermissionsByRoleId(roleId);
    }

    @Override
    public List<String> getRolesByPermissionId(String permissionId) {
        return rolePermissionsDao.selectRolesByPermissionId(permissionId);
    }

    @Override
    public int removePermissionFromRole(String roleId, String permissionId) {
        return rolePermissionsDao.delete(roleId, permissionId);
    }

    @Override
    public int removeAllPermissionsFromRole(String roleId) {
        return rolePermissionsDao.deleteByRoleId(roleId);
    }
}