package com.gosaint.web.permissionManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.menus.Menus;
import com.gosaint.service.menus.MenusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/menus")
@Tag(name = "菜单管理", description = "菜单相关操作接口")
@Slf4j
public class MenusController {

    @Autowired
    private MenusService menusService;

    /**
     * 获取所有菜单（树形结构）
     * @return 菜单树列表
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有菜单", description = "获取系统中所有菜单数据，返回树形结构")
    public ResponseEntity<List<Menus>> getAllMenus() {
        List<Menus> menus = menusService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    /**
     * 获取所有菜单
     * @return 菜单树列表
     */
    @GetMapping("/all/pages")
    @Operation(summary = "获取所有菜单", description = "获取系统中所有菜单数据")
    public ResponseEntity<PageInfo<Menus>> getAllMenusByPage(int pageNum, int pageSize) {
        log.info("菜单分页接口获取的参数对象：pageNum=={}，pageSize=={}", pageNum, pageSize);
        return ResponseEntity.ok(menusService.getAllMenusByPage(pageNum,pageSize));
    }

    /**
     * 根据ID查询菜单
     * @param menuId 菜单ID
     * @return 菜单对象
     */
    @GetMapping("/{menuId}")
    @Operation(summary = "根据ID查询菜单", description = "根据菜单ID查询单个菜单详情")
    public ResponseEntity<Menus> getMenuById(@PathVariable String menuId) {
        Menus menu = menusService.getMenuById(menuId);
        return menu != null ? ResponseEntity.ok(menu) : ResponseEntity.notFound().build();
    }

    /**
     * 根据父菜单ID查询子菜单
     * @param parentId 父菜单ID
     * @return 子菜单列表
     */
    @GetMapping("/submenus")
    @Operation(summary = "根据父菜单ID查询子菜单", description = "根据父菜单ID查询其下所有子菜单")
    public ResponseEntity<List<Menus>> getSubMenusByParentId(@RequestParam("parentId") String parentId) {
        List<Menus> subMenus = menusService.getSubMenusByParentId(parentId);
        return ResponseEntity.ok(subMenus);
    }

    /**
     * 根据角色ID查询菜单列表
     * @param roleId 角色ID
     * @return 菜单树列表
     */
    @GetMapping("/by-role/{roleId}")
    @Operation(summary = "根据角色ID查询菜单", description = "根据角色ID查询该角色拥有的菜单权限")
    public ResponseEntity<List<Menus>> getMenusByRoleId(@PathVariable String roleId) {
        List<Menus> menus = menusService.getMenusByRoleId(roleId);
        return ResponseEntity.ok(menus);
    }

    /**
     * 创建菜单
     * @param menu 菜单对象
     * @return 创建结果
     */
    @PostMapping
    @Operation(summary = "创建菜单", description = "创建新的菜单")
    public ResponseEntity<Integer> createMenu(@RequestBody Menus menu) {
        int result = menusService.createMenu(menu);
        return ResponseEntity.ok(result);
    }

    /**
     * 更新菜单
     * @param menuId 菜单ID
     * @param menu 菜单对象
     * @return 更新结果
     */
    @PutMapping("/{menuId}")
    @Operation(summary = "更新菜单", description = "更新现有菜单信息")
    public ResponseEntity<Integer> updateMenu(@PathVariable String menuId, @RequestBody Menus menu) {
        menu.setMenuId(menuId);
        int result = menusService.updateMenu(menu);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    /**
     * 删除菜单
     * @param menuId 菜单ID
     * @return 删除结果
     */
    @DeleteMapping("/{menuId}")
    @Operation(summary = "删除菜单", description = "删除指定ID的菜单")
    public ResponseEntity<Integer> deleteMenu(@PathVariable String menuId) {
        int result = menusService.deleteMenu(menuId);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}