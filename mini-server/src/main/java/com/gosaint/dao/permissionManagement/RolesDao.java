package com.gosaint.dao.permissionManagement;

import com.gosaint.model.permissionManagement.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolesDao {
    // 创建角色
    int insert(Roles role);

    // 根据ID查询角色
    Roles selectById(@Param("roleId") String roleId);

    // 根据角色名称查询角色
    Roles selectByName(@Param("roleName") String roleName);

    // 查询所有角色
    List<Roles> selectAll();

    // 更新角色
    int update(Roles role);

    // 删除角色
    int delete(@Param("roleId") String roleId);
}