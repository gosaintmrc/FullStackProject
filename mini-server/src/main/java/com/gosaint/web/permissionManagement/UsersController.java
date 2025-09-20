package com.gosaint.web.permissionManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.permissionManagement.Users;
import com.gosaint.model.vo.UsersVO;
import com.gosaint.service.permissionManagement.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户相关的API接口")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Operation(summary = "创建用户", description = "添加新用户到系统")
    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody UsersVO user) {
        log.info("新增用户对象：{}",user);
        int result = usersService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "根据ID获取用户", description = "通过用户ID查询用户详情")
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@Parameter(description = "用户ID") @PathVariable String userId) {
        Users user = usersService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "分页获取所有用户", description = "分页查询系统中的所有用户列表")
    @GetMapping
    public ResponseEntity<PageInfo<UsersVO>> getAllUsersAndRoles(int pageNum, int pageSize) {
        log.debug("获取所有用户start------");
        PageInfo<UsersVO> page = usersService.getAllUsersAndRoles(pageNum, pageSize);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "更新用户", description = "修改用户信息")
    @PutMapping
    public ResponseEntity<Integer> updateUser(@RequestBody Users user) {
        log.debug("PUT /api/users called by principal");
        int result = usersService.updateUser(user);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteUser(@Parameter(description = "用户ID") @PathVariable String userId) {
        int result = usersService.deleteUser(userId);
        return ResponseEntity.ok(result);
    }
}