package com.gosaint.web.inventoryManagement;

import com.gosaint.model.inventoryManagement.Warehouses;
import com.gosaint.service.inventoryManagement.WarehousesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "仓库管理", description = "仓库相关的CRUD操作")
@RestController
@RequestMapping("/api/warehouses")
public class WarehousesController {
    
    @Autowired
    private WarehousesService warehousesService;

    @Operation(summary = "创建仓库", description = "添加新的仓库信息到系统")
    @ApiResponse(responseCode = "200", description = "仓库创建成功", content = @Content(schema = @Schema(implementation = Warehouses.class)))
    @PostMapping
    public ResponseEntity<Warehouses> createWarehouse(
            @Parameter(description = "仓库对象，包含仓库名称、地址等信息", required = true, schema = @Schema(implementation = Warehouses.class))
            @RequestBody Warehouses warehouse) {
        Warehouses createdWarehouse = warehousesService.createWarehouse(warehouse);
        return ResponseEntity.ok(createdWarehouse);
    }

    @Operation(summary = "根据ID查询仓库", description = "通过仓库ID获取仓库详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = Warehouses.class)))
    @ApiResponse(responseCode = "404", description = "仓库不存在")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouses> getWarehouseById(
            @Parameter(description = "仓库唯一标识符", required = true) @PathVariable("id") String id) {
        Warehouses warehouse = warehousesService.getWarehouseById(id);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(warehouse);
    }

    @Operation(summary = "查询所有仓库", description = "获取系统中所有仓库的列表")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = Warehouses[].class)))
    @GetMapping
    public ResponseEntity<List<Warehouses>> getAllWarehouses() {
        List<Warehouses> warehouses = warehousesService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @Operation(summary = "更新仓库", description = "更新现有仓库的信息")
    @ApiResponse(responseCode = "200", description = "仓库更新成功", content = @Content(schema = @Schema(implementation = Warehouses.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Warehouses> updateWarehouse(
            @Parameter(description = "仓库唯一标识符", required = true) @PathVariable("id") String id,
            @Parameter(description = "更新后的仓库信息", required = true, schema = @Schema(implementation = Warehouses.class)) @RequestBody Warehouses warehouse) {
        warehouse.setWarehouseId(id);
        Warehouses updatedWarehouse = warehousesService.updateWarehouse(warehouse);
        return ResponseEntity.ok(updatedWarehouse);
    }

    @Operation(summary = "删除仓库", description = "从系统中删除指定的仓库")
    @ApiResponse(responseCode = "204", description = "仓库删除成功")
    @ApiResponse(responseCode = "404", description = "仓库不存在")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(
            @Parameter(description = "仓库唯一标识符", required = true) @PathVariable("id") String id) {
        boolean deleted = warehousesService.deleteWarehouse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}