package com.gosaint.service.permissionManagement.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gosaint.dao.permissionManagement.RolesDao;
import com.gosaint.model.permissionManagement.Roles;
import com.gosaint.model.permissionManagement.Users;
import com.gosaint.service.permissionManagement.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesDao rolesDao;

    @Override
    public int createRole(Roles role) {
        return rolesDao.insert(role);
    }

    @Override
    public Roles getRoleById(String roleId) {
        return rolesDao.selectById(roleId);
    }

    @Override
    public Roles getRoleByName(String roleName) {
        return rolesDao.selectByName(roleName);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesDao.selectAll();
    }

    @Override
    public PageInfo<Roles> getAllRolesByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(rolesDao.selectAll());
    }

    @Override
    public int updateRole(Roles role) {
        return rolesDao.update(role);
    }

    @Override
    public int deleteRole(String roleId) {
        return rolesDao.delete(roleId);
    }
}