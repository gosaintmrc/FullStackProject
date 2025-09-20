package com.gosaint.dao.menus;

import com.gosaint.model.menus.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenusDao {
    // 创建菜单
    int insert(Menus menu);

    // 根据ID查询菜单
    Menus selectById(@Param("menuId") String menuId);

    // 根据父菜单ID查询子菜单
    List<Menus> selectByParentId(@Param("parentId") String parentId);

    // 查询所有菜单（树形结构）
    List<Menus> selectAllMenus();
    // 查询所有菜单
    List<Menus> selectAllMenusByPage();

    // 更新菜单
    int update(Menus menu);

    // 删除菜单
    int delete(@Param("menuId") String menuId);
    
    // 根据角色ID查询菜单列表
    List<Menus> selectMenusByRoleId(@Param("roleId") String roleId);
}