package com.gosaint.model.permissionManagement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "角色-权限关联实体类")
public class RolePermissions {
    @Schema(description = "角色唯一标识")
    private String roleId;
    @Schema(description = "权限唯一标识")
    private String permissionId;
}