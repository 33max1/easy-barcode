package com.easybarcode.exception;

/**
 * 条码导出异常
 */
public class BarcodeExportException extends RuntimeException {
    public BarcodeExportException(String message) {
        super(message);
    }

    public BarcodeExportException(String message, Throwable cause) {
        super(message, cause);
    }
}