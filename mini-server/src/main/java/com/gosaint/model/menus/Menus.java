package com.gosaint.model.menus;

import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "菜单实体类")
public class Menus {
    @Schema(description = "菜单唯一标识")
    private String menuId;
    
    @Schema(description = "菜单名称")
    private String menuName;
    
    @Schema(description = "菜单图标")
    private String icon;
    
    @Schema(description = "菜单路径")
    private String path;
    
    @Schema(description = "上级菜单ID")
    private String parentId;
    
    @Schema(description = "菜单排序")
    private Integer sortOrder;
    
    @Schema(description = "是否显示")
    private Boolean isVisible;
    
    @Schema(description = "菜单类型")
    private String menuType;
    
    @Schema(description = "权限标识")
    private String permissionKey;
    
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
    
    // 子菜单列表
    private List<Menus> children;
}