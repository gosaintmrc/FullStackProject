package com.gosaint.model.vo;

import com.gosaint.model.permissionManagement.Users;
import lombok.Data;

@Data
public class UsersVO extends Users {

    private String roleName;
    private String roleId;
    private String description;
}
