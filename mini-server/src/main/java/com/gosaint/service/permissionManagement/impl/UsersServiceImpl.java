package com.gosaint.service.permissionManagement.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gosaint.dao.permissionManagement.UsersDao;
import com.gosaint.model.permissionManagement.Users;
import com.gosaint.model.vo.UsersVO;
import com.gosaint.service.permissionManagement.UserRoleService;
import com.gosaint.service.permissionManagement.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public int createUser(Users user) {
        return usersDao.insert(user);
    }

    @Override
    @Transactional
    public int createUser(UsersVO user) {
        return 0;
    }

    @Override
    public Users getUserById(String userId) {
        return usersDao.selectById(userId);
    }

    @Override
    public Users getUserByUsername(String username) {
        return usersDao.selectByUsername(username);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersDao.selectAll();
    }

    @Override
    public PageInfo<UsersVO> getAllUsersAndRoles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UsersVO> users = usersDao.selectAndRolesAll();
        return new PageInfo<>(users);
    }

    @Override
    public PageInfo<Users> getAllUsersByPage(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> users = usersDao.selectAll();
        return new PageInfo<>(users);
    }

    @Override
    public int updateUser(Users user) {
        return usersDao.update(user);
    }

    @Override
    @Transactional
    public int deleteUser(String userId) {
        //此时也需要删除对应的用户角色这个表的数据
        userRoleService.removeAllRolesFromUser(userId);
        return usersDao.delete(userId);
    }
}