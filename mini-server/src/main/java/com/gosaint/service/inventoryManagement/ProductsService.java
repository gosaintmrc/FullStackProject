package com.gosaint.service.inventoryManagement;

import com.github.pagehelper.PageInfo;
import com.gosaint.model.inventoryManagement.Products;
import com.gosaint.model.vo.ExcelImportResult;
import com.gosaint.model.vo.ProductsVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductsService {
    // 创建产品
    Products createProduct(Products product);

    // 根据ID查询产品
    Products getProductById(String productId);

    // 查询所有产品
    List<Products> getAllProducts();

    // 查询所有产品分页
    PageInfo<ProductsVO> getAllProductsByPage(int pageNum, int pageSize, String search);

    // 更新产品
    Products updateProduct(Products product);

    // 删除产品
    boolean deleteProduct(String productId);
    
    // Excel导入产品
    ExcelImportResult importProductsFromExcel(MultipartFile file);
}