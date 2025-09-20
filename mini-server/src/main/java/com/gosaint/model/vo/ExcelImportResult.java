package com.gosaint.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Excel导入结果")
public class ExcelImportResult {
    @Schema(description = "导入是否成功")
    private boolean success;
    
    @Schema(description = "成功导入的数量")
    private int successCount;
    
    @Schema(description = "失败的数量")
    private int failureCount;
    
    @Schema(description = "总数量")
    private int totalCount;
    
    @Schema(description = "错误信息列表")
    private List<String> errorMessages;
    
    @Schema(description = "成功消息")
    private String message;
    
    public ExcelImportResult() {
        this.success = true;
        this.successCount = 0;
        this.failureCount = 0;
        this.totalCount = 0;
    }
    
    public ExcelImportResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public void incrementSuccess() {
        this.successCount++;
        this.totalCount++;
    }
    
    public void incrementFailure() {
        this.failureCount++;
        this.totalCount++;
        this.success = false;
    }
}