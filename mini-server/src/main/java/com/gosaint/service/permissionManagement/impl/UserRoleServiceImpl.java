package com.gosaint.service.permissionManagement.impl;

import com.gosaint.dao.permissionManagement.UserRolesDao;
import com.gosaint.model.permissionManagement.UserRoles;
import com.gosaint.service.permissionManagement.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRolesDao userRolesDao;

    @Override
    public int addRoleToUser(String userId, String roleId) {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(userId);
        userRoles.setRoleId(roleId);
        return userRolesDao.insert(userRoles);
    }

    @Override
    public List<String> getRolesByUserId(String userId) {
        return userRolesDao.selectRolesByUserId(userId);
    }

    @Override
    public List<String> getUsersByRoleId(String roleId) {
        return userRolesDao.selectUsersByRoleId(roleId);
    }

    @Override
    public int removeRoleFromUser(String userId, String roleId) {
        return userRolesDao.delete(userId, roleId);
    }

    @Override
    public int removeAllRolesFromUser(String userId) {
        return userRolesDao.deleteByUserId(userId);
    }
}