package com.gosaint.dao.inventoryManagement;

import com.gosaint.model.inventoryManagement.Warehouses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehousesDao {
    // 创建仓库
    int insert(Warehouses warehouse);

    // 根据ID查询仓库
    Warehouses selectById(@Param("warehouseId") String warehouseId);

    // 查询所有仓库
    List<Warehouses> selectAll();

    // 更新仓库
    int update(Warehouses warehouse);

    // 删除仓库
    int delete(@Param("warehouseId") String warehouseId);
}