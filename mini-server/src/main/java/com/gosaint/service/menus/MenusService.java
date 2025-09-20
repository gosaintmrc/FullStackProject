package com.gosaint.service.menus;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gosaint.dao.menus.MenusDao;
import com.gosaint.model.menus.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MenusService {

    @Autowired
    private MenusDao menusDao;

    /**
     * 创建菜单
     * @param menu 菜单对象
     * @return 创建结果
     */
    public int createMenu(Menus menu) {
        menu.setMenuId(UUID.randomUUID().toString());
        menu.setCreatedAt(OffsetDateTime.now());
        return menusDao.insert(menu);
    }

    /**
     * 根据ID查询菜单
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    public Menus getMenuById(String menuId) {
        return menusDao.selectById(menuId);
    }

    /**
     * 根据父菜单ID查询子菜单
     * @param parentId 父菜单ID
     * @return 子菜单列表
     */
    public List<Menus> getSubMenusByParentId(String parentId) {
        return menusDao.selectByParentId(parentId);
    }

    /**
     * 获取所有菜单（树形结构）
     * @return 菜单树列表
     */
    public List<Menus> getAllMenus() {
        return menusDao.selectAllMenus();
    }

    /**
     * 获取所有菜单
     * @return 菜单树列表
     */
    public PageInfo<Menus> getAllMenusByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menus> menus = menusDao.selectAllMenusByPage();
        return new PageInfo<>(menus);
    }

    /**
     * 更新菜单
     * @param menu 菜单对象
     * @return 更新结果
     */
    public int updateMenu(Menus menu) {
        return menusDao.update(menu);
    }

    /**
     * 删除菜单
     * @param menuId 菜单ID
     * @return 删除结果
     */
    public int deleteMenu(String menuId) {
        return menusDao.delete(menuId);
    }

    /**
     * 根据角色ID查询菜单列表
     * @param roleId 角色ID
     * @return 菜单树列表
     */
    public List<Menus> getMenusByRoleId(String roleId) {
        return menusDao.selectMenusByRoleId(roleId);
    }
}