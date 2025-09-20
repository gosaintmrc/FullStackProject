package com.gosaint.model.permissionManagement;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "权限实体类")
public class Permissions {
    @Schema(description = "权限唯一标识")
    private String permissionId;
    @Schema(description = "权限名称")
    private String permissionName;
    @Schema(description = "权限描述")
    private String description;
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
}