package com.gosaint.service.inventoryManagement;

import com.gosaint.model.inventoryManagement.Warehouses;

import java.util.List;

public interface WarehousesService {
    // 创建仓库
    Warehouses createWarehouse(Warehouses warehouse);

    // 根据ID查询仓库
    Warehouses getWarehouseById(String warehouseId);

    // 查询所有仓库
    List<Warehouses> getAllWarehouses();

    // 更新仓库
    Warehouses updateWarehouse(Warehouses warehouse);

    // 删除仓库
    boolean deleteWarehouse(String warehouseId);
}