package com.gosaint.service.inventoryManagement;

import com.gosaint.model.inventoryManagement.InventoryItems;

import java.util.List;

public interface InventoryItemsService {
    // 创建库存条目
    InventoryItems createInventoryItem(InventoryItems inventoryItem);

    // 根据ID查询库存条目
    InventoryItems getInventoryItemById(String itemId);

    // 查询所有库存条目
    List<InventoryItems> getAllInventoryItems();

    // 更新库存条目
    InventoryItems updateInventoryItem(InventoryItems inventoryItem);

    // 删除库存条目
    boolean deleteInventoryItem(String itemId);

    // 根据产品ID查询库存
    List<InventoryItems> getInventoryItemsByProductId(String productId);

    // 根据仓库ID查询库存
    List<InventoryItems> getInventoryItemsByWarehouseId(String warehouseId);
}