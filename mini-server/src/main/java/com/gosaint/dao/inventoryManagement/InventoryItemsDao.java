package com.gosaint.dao.inventoryManagement;

import com.gosaint.model.inventoryManagement.InventoryItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryItemsDao {
    // 创建库存条目
    int insert(InventoryItems inventoryItem);

    // 根据ID查询库存条目
    InventoryItems selectById(@Param("itemId") String itemId);

    // 查询所有库存条目
    List<InventoryItems> selectAll();

    // 更新库存条目
    int update(InventoryItems inventoryItem);

    // 删除库存条目
    int delete(@Param("itemId") String itemId);

    // 根据产品ID查询库存
    List<InventoryItems> selectByProductId(@Param("productId") String productId);

    List<String> selectByProductIds(@Param("productIdList") List<String> productIds);

    // 根据仓库ID查询库存
    List<InventoryItems> selectByWarehouseId(@Param("warehouseId") String warehouseId);
}