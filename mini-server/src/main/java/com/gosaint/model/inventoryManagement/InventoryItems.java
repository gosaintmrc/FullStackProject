package com.gosaint.model.inventoryManagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "库存条目实体类")
public class InventoryItems {

    @Schema(description = "库存条目唯一标识")
    private String itemId;
    @Schema(description = "产品ID")
    private String productId;
    @Schema(description = "批次号")
    private String batchNumber;
    @Schema(description = "序列号")
    private String serialNumber;
    @Schema(description = "数量")
    private Integer quantity;
    @Schema(description = "计量单位")
    private String unitOfMeasure;
    @Schema(description = "仓库ID")
    private String warehouseId;
    @Schema(description = "过期日期")
    private LocalDate expiryDate;
    @Schema(description = "入库日期")
    private OffsetDateTime entryDate;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "成本价格")
    private BigDecimal costPrice;
    @Schema(description = "是否可追溯")
    private Boolean isTraceable;
}