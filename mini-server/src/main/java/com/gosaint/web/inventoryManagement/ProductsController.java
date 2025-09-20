package com.gosaint.web.inventoryManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.inventoryManagement.Products;
import com.gosaint.model.vo.ExcelImportResult;
import com.gosaint.model.vo.ProductsVO;
import com.gosaint.service.inventoryManagement.ProductsService;
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
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "产品管理", description = "产品相关的CRUD操作")
@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductsController {
    
    @Autowired
    private ProductsService productsService;

    @Operation(summary = "创建产品", description = "添加新的产品信息到系统")
    @ApiResponse(responseCode = "200", description = "产品创建成功", content = @Content(schema = @Schema(implementation = Products.class)))
    @PostMapping
    public ResponseEntity<Products> createProduct(
            @Parameter(description = "产品对象，包含产品名称、SKU等信息", required = true, schema = @Schema(implementation = Products.class))
            @RequestBody Products product) {
        Products createdProduct = productsService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @Operation(summary = "根据ID查询产品", description = "通过产品ID获取产品详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = Products.class)))
    @ApiResponse(responseCode = "404", description = "产品不存在")
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(
            @Parameter(description = "产品唯一标识符", required = true) @PathVariable("id") String id) {
        Products product = productsService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "分页查询所有产品", description = "分页获取系统中所有产品的列表")
    @ApiResponse(responseCode = "200", description = "查询成功", content = @Content(schema = @Schema(implementation = Products[].class)))
    @GetMapping
    public ResponseEntity<PageInfo<ProductsVO>> getAllProducts(int pageNum, int pageSize, String search) {
        log.info("search={}", search);
        PageInfo<ProductsVO> products = productsService.getAllProductsByPage(pageNum,pageSize,search);
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "更新产品", description = "更新现有产品的信息")
    @ApiResponse(responseCode = "200", description = "产品更新成功", content = @Content(schema = @Schema(implementation = Products.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(
            @Parameter(description = "产品唯一标识符", required = true) @PathVariable("id") String id,
            @Parameter(description = "更新后的产品信息", required = true, schema = @Schema(implementation = Products.class)) @RequestBody Products product) {
        product.setProductId(id);
        Products updatedProduct = productsService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "删除产品", description = "从系统中删除指定的产品")
    @ApiResponse(responseCode = "204", description = "产品删除成功")
    @ApiResponse(responseCode = "404", description = "产品不存在")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "产品唯一标识符", required = true) @PathVariable("id") String id) {
        boolean deleted = productsService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "Excel导入产品", description = "通过Excel文件批量导入产品数据")
    @ApiResponse(responseCode = "200", description = "导入成功", content = @Content(schema = @Schema(implementation = ExcelImportResult.class)))
    @ApiResponse(responseCode = "400", description = "文件格式错误或数据验证失败")
    @PostMapping("/import")
    public ResponseEntity<ExcelImportResult> importProducts(
            @Parameter(description = "产品Excel文件，支持.xlsx和.xls格式", required = true)
            @RequestParam("file") MultipartFile file) {
        
        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new ExcelImportResult(false, "请选择要导入的Excel文件"));
        }
        
        ExcelImportResult result = productsService.importProductsFromExcel(file);
        return ResponseEntity.ok(result);
    }
}