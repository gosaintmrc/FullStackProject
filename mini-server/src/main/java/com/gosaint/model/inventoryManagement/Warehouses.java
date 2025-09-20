package com.gosaint.model.inventoryManagement;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "仓库实体类")
public class Warehouses {
    @Schema(description = "仓库唯一标识")
    private String warehouseId;
    @Schema(description = "仓库名称")
    private String warehouseName;
    @Schema(description = "仓库地址")
    private String address;
    @Schema(description = "创建时间")
    private OffsetDateTime createdAt;
}