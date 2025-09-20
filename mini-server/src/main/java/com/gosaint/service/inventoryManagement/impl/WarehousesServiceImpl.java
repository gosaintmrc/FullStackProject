package com.gosaint.service.inventoryManagement.impl;

import com.gosaint.dao.inventoryManagement.WarehousesDao;
import com.gosaint.model.inventoryManagement.Warehouses;
import com.gosaint.service.inventoryManagement.WarehousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WarehousesServiceImpl implements WarehousesService {
    
    @Autowired
    private WarehousesDao warehousesDao;

    @Override
    public Warehouses createWarehouse(Warehouses warehouse) {
        if (warehouse.getWarehouseId() == null) {
            warehouse.setWarehouseId(UUID.randomUUID().toString());
        }
        warehousesDao.insert(warehouse);
        return warehousesDao.selectById(warehouse.getWarehouseId());
    }

    @Override
    public Warehouses getWarehouseById(String warehouseId) {
        return warehousesDao.selectById(warehouseId);
    }

    @Override
    public List<Warehouses> getAllWarehouses() {
        return warehousesDao.selectAll();
    }

    @Override
    public Warehouses updateWarehouse(Warehouses warehouse) {
        warehousesDao.update(warehouse);
        return warehousesDao.selectById(warehouse.getWarehouseId());
    }

    @Override
    public boolean deleteWarehouse(String warehouseId) {
        int result = warehousesDao.delete(warehouseId);
        return result > 0;
    }
}