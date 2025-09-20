package com.gosaint.dao.inventoryManagement;

import com.gosaint.model.inventoryManagement.Products;
import com.gosaint.model.vo.ProductsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsDao {
    // 创建产品
    int insert(Products product);

    // 批量插入产品
    int insertBatch(@Param("products") List<Products> products);

    // 根据ID查询产品
    Products selectById(@Param("productId") String productId);

    // 根据SKU查询产品
    Products selectBySku(@Param("sku") String sku);

    // 查询所有产品
    List<Products> selectAll();

    List<ProductsVO> selectAllWithSearch(@Param("search") String search);

    // 更新产品
    int update(Products product);

    // 删除产品
    int delete(@Param("productId") String productId);
}