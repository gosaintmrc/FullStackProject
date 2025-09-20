package com.gosaint.model.inventoryManagement;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "产品实体类")
public class Products {
    @Schema(description = "产品唯一标识")
    private String productId;
    @Schema(description = "产品名称")
    private String productName;
    @Schema(description = "库存单元编号")
    private String sku;
    @Schema(description = "品牌")
    private String brand;
    @Schema(description = "分类")
    private String category;
    @Schema(description = "供应商")
    private String supplier;
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
}