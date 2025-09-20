package com.gosaint.dao.permissionManagement;

import com.gosaint.model.permissionManagement.Permissions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionsDao {
    // 创建权限
    int insert(Permissions permission);

    // 根据ID查询权限
    Permissions selectById(@Param("permissionId") String permissionId);

    // 根据权限名称查询权限
    Permissions selectByName(@Param("permissionName") String permissionName);

    // 查询所有权限
    List<Permissions> selectAll();

    // 更新权限
    int update(Permissions permission);

    // 删除权限
    int delete(@Param("permissionId") String permissionId);
}