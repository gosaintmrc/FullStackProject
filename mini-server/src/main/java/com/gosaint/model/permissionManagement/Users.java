package com.gosaint.model.permissionManagement;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户实体类")
public class Users {
    @Schema(description = "用户唯一标识")
    private String userId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码哈希")
    private String passwordHash;
    @Schema(description = "电子邮箱")
    private String email;
    @Schema(description = "用户全名")
    private String fullName;
    @Schema(description = "是否激活")
    private Boolean isActive;
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
}