package com.gosaint.web.inventoryManagement;

import com.gosaint.model.inventoryManagement.InventoryItems;
import com.gosaint.service.inventoryManagement.InventoryItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "库存管理", description = "库存相关的CRUD操作和查询功能")
@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
@Slf4j
public class InventoryItemsController {
    
    @Autowired
    private InventoryItemsService inventoryItemsService;

    @Operation(summary = "创建库存条目", description = "添加新的库存物品信息到系统")
    @ApiResponse(responseCode = "200", description = "库存条目创建成功", content = @Content(schema = @Schema(implementation = InventoryItems.class)))
    @PostMapping
    public ResponseEntity<String> createInventoryItem(
            @Parameter(description = "库存条目对象，包含产品信息、数量等", required = true, schema = @Schema(implementation = InventoryItems.class))
            @RequestBody InventoryItems inventoryItem) {
        log.info("提交的参数对象：{}", inventoryItem.toString());
        inventoryItemsService.createInventoryItem(inventoryItem);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "根据ID查询库存条目", description = "通过ID获取库存条目的详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = InventoryItems.class)))
    @ApiResponse(responseCode = "404", description = "库存条目不存在")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItems> getInventoryItemById(
            @Parameter(description = "库存条目唯一标识符", required = true) @PathVariable("id") String id) {
        InventoryItems inventoryItem = inventoryItemsService.getInventoryItemById(id);
        if (inventoryItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventoryItem);
    }

    @Operation(summary = "查询所有库存条目", description = "获取系统中所有库存条目的列表")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = InventoryItems[].class)))
    @GetMapping
    public ResponseEntity<List<InventoryItems>> getAllInventoryItems() {
        List<InventoryItems> inventoryItems = inventoryItemsService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    @Operation(summary = "更新库存条目", description = "更新现有库存条目的信息")
    @ApiResponse(responseCode = "200", description = "库存条目更新成功", content = @Content(schema = @Schema(implementation = InventoryItems.class)))
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItems> updateInventoryItem(
            @Parameter(description = "库存条目唯一标识符", required = true) @PathVariable("id") String id,
            @Parameter(description = "更新后的库存条目信息", required = true, schema = @Schema(implementation = InventoryItems.class)) @RequestBody InventoryItems inventoryItem) {
        inventoryItem.setItemId(id);
        InventoryItems updatedItem = inventoryItemsService.updateInventoryItem(inventoryItem);
        return ResponseEntity.ok(updatedItem);
    }

    @Operation(summary = "删除库存条目", description = "从系统中删除指定的库存条目")
    @ApiResponse(responseCode = "204", description = "库存条目删除成功")
    @ApiResponse(responseCode = "404", description = "库存条目不存在")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(
            @Parameter(description = "库存条目唯一标识符", required = true) @PathVariable("id") String id) {
        boolean deleted = inventoryItemsService.deleteInventoryItem(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "根据产品ID查询库存", description = "获取指定产品在所有仓库中的库存信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = InventoryItems[].class)))
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryItems>> getInventoryItemsByProductId(
            @Parameter(description = "产品唯一标识符", required = true) @PathVariable String productId) {
        List<InventoryItems> items = inventoryItemsService.getInventoryItemsByProductId(productId);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "根据仓库ID查询库存", description = "获取指定仓库中所有产品的库存信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = InventoryItems[].class)))
    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryItems>> getInventoryItemsByWarehouseId(
            @Parameter(description = "仓库唯一标识符", required = true) @PathVariable String warehouseId) {
        List<InventoryItems> items = inventoryItemsService.getInventoryItemsByWarehouseId(warehouseId);
        return ResponseEntity.ok(items);
    }
}