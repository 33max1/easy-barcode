package com.easybarcode.core;

/**
 * 支持的条码类型枚举
 */
public enum BarcodeType {
    CODE_128,       // Code 128
    QR_CODE,        // QR Code
    EAN_13,         // EAN-13
    UPC_A,          // UPC-A
    CODE_39,        // Code 39
    ITF,            // ITF (Interleaved 2 of 5)
    PDF_417,        // PDF417
    DATA_MATRIX,    // Data Matrix
    AZTEC           // Aztec Code
}