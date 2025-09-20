package com.gosaint.model.permissionManagement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户-角色关联实体类")
public class UserRoles {
    @Schema(description = "用户唯一标识")
    private String userId;
    @Schema(description = "角色唯一标识")
    private String roleId;
}