package com.gosaint.dao.permissionManagement;

import com.gosaint.model.permissionManagement.UserRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRolesDao {
    // 为用户添加角色
    int insert(UserRoles userRoles);

    // 根据用户ID查询角色列表
    List<String> selectRolesByUserId(@Param("userId") String userId);

    // 根据角色ID查询用户列表
    List<String> selectUsersByRoleId(@Param("roleId") String roleId);

    // 解除用户-角色关联
    int delete(@Param("userId") String userId, @Param("roleId") String roleId);

    // 解除用户的所有角色关联
    int deleteByUserId(@Param("userId") String userId);
}