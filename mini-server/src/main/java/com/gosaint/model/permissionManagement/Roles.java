package com.gosaint.model.permissionManagement;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "角色实体类")
public class Roles {
    @Schema(description = "角色唯一标识")
    private String roleId;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色描述")
    private String description;
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
}