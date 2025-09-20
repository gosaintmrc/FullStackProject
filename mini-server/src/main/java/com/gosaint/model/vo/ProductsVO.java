package com.gosaint.model.vo;

import com.gosaint.model.inventoryManagement.Products;
import lombok.Data;

@Data
public class ProductsVO extends Products {

    /** 是否入库 */
    private boolean isIntoInventory = false;
}
