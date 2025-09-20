package com.gosaint.service.inventoryManagement.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gosaint.dao.inventoryManagement.ProductsDao;
import com.gosaint.model.inventoryManagement.Products;
import com.gosaint.model.vo.ExcelImportResult;
import com.gosaint.model.vo.ProductsVO;
import com.gosaint.service.inventoryManagement.ProductsService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsDao productsDao;


    @Override
    public Products createProduct(Products product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productsDao.insert(product);
        return productsDao.selectById(product.getProductId());
    }

    @Override
    public Products getProductById(String productId) {
        return productsDao.selectById(productId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productsDao.selectAll();
    }

    @Override
    public PageInfo<ProductsVO> getAllProductsByPage(int pageNum, int pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductsVO> productList = productsDao.selectAllWithSearch(search);
        return new PageInfo<>(productList);
    }

    @Override
    public Products updateProduct(Products product) {
        productsDao.update(product);
        return productsDao.selectById(product.getProductId());
    }

    @Override
    public boolean deleteProduct(String productId) {
        int result = productsDao.delete(productId);
        return result > 0;
    }

    @Override
    @Transactional
    public ExcelImportResult importProductsFromExcel(MultipartFile file) {
        ExcelImportResult result = new ExcelImportResult();
        List<String> errorMessages = new ArrayList<>();
        List<Products> validProducts = new ArrayList<>();

        try {
            // 验证文件类型
            if (!isValidExcelFile(file)) {
                return new ExcelImportResult(false, "请上传有效的Excel文件(.xlsx或.xls)");
            }

            Workbook workbook = createWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            // 跳过标题行，从第二行开始读取数据
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || isEmptyRow(row)) {
                    continue;
                }

                try {
                    Products product = parseRowToProduct(row, i + 1);

                    // 验证产品数据
                    String validationError = validateProduct(product, i + 1);
                    if (validationError != null) {
                        errorMessages.add(validationError);
                        result.incrementFailure();
                        continue;
                    }

                    // 检查SKU是否已存在
                    if (productsDao.selectBySku(product.getSku()) != null) {
                        errorMessages.add(String.format("第%d行：SKU '%s' 已存在", i + 1, product.getSku()));
                        result.incrementFailure();
                        continue;
                    }

                    validProducts.add(product);
                    result.incrementSuccess();

                } catch (Exception e) {
                    errorMessages.add(String.format("第%d行：数据解析错误 - %s", i + 1, e.getMessage()));
                    result.incrementFailure();
                }
            }

            workbook.close();

            // 批量插入有效产品
            if (!validProducts.isEmpty()) {
                productsDao.insertBatch(validProducts);
            }

            result.setErrorMessages(errorMessages);

            if (result.getFailureCount() == 0) {
                result.setMessage(String.format("成功导入%d个产品", result.getSuccessCount()));
            } else {
                result.setMessage(String.format("导入完成：成功%d个，失败%d个",
                        result.getSuccessCount(), result.getFailureCount()));
            }

        } catch (IOException e) {
            return new ExcelImportResult(false, "文件读取失败：" + e.getMessage());
        } catch (Exception e) {
            return new ExcelImportResult(false, "导入过程中发生错误：" + e.getMessage());
        }

        return result;
    }

    private boolean isValidExcelFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename != null && (filename.endsWith(".xlsx") || filename.endsWith(".xls"));
    }

    private Workbook createWorkbook(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (filename != null && filename.endsWith(".xlsx")) {
            return new XSSFWorkbook(file.getInputStream());
        } else {
            return new HSSFWorkbook(file.getInputStream());
        }
    }

    private boolean isEmptyRow(Row row) {
        for (int i = 0; i < 5; i++) { // 检查前5列是否都为空
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK &&
                    !getCellValueAsString(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Products parseRowToProduct(Row row, int rowNum) {
        Products product = new Products();
        product.setProductId(UUID.randomUUID().toString());
        product.setCreatedAt(OffsetDateTime.now());

        // 解析Excel列数据（按照模板顺序：商品名称、SKU、分类、品牌、供应商）
        product.setProductName(getCellValueAsString(row.getCell(0)).trim());
        product.setSku(getCellValueAsString(row.getCell(1)).trim());
        product.setCategory(getCellValueAsString(row.getCell(2)).trim());
        product.setBrand(getCellValueAsString(row.getCell(3)).trim());
        product.setSupplier(getCellValueAsString(row.getCell(4)).trim());

        return product;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private String validateProduct(Products product, int rowNum) {
        // 验证必填字段
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            return String.format("第%d行：商品名称不能为空", rowNum);
        }

        if (product.getSku() == null || product.getSku().trim().isEmpty()) {
            return String.format("第%d行：SKU不能为空", rowNum);
        }

        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            return String.format("第%d行：商品分类不能为空", rowNum);
        }

        // 验证字段长度
        if (product.getProductName().length() > 100) {
            return String.format("第%d行：商品名称长度不能超过100个字符", rowNum);
        }

        if (product.getSku().length() > 50) {
            return String.format("第%d行：SKU长度不能超过50个字符", rowNum);
        }

        return null; // 验证通过
    }
}