package com.gosaint.dao.permissionManagement;

import com.gosaint.model.permissionManagement.Users;
import com.gosaint.model.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDao {
    // 创建用户
    int insert(Users user);

    // 根据ID查询用户
    Users selectById(@Param("userId") String userId);

    // 根据用户名查询用户
    Users selectByUsername(@Param("username") String username);

    // 查询所有用户
    List<Users> selectAll();

    List<UsersVO> selectAndRolesAll();

    // 更新用户
    int update(Users user);

    // 删除用户
    int delete(@Param("userId") String userId);
}