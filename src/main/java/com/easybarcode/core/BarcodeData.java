package com.easybarcode.core;

/**
 * 条码数据类
 */
public class BarcodeData {
    private String content; // 条码内容
    private BarcodeType type; // 条码类型
    private BarcodeConfig config; // 条码配置

    // 构造函数、getter和setter方法

    public BarcodeData() {
    }

    public BarcodeData(String content, BarcodeType type) {
        this.content = content;
        this.type = type;
        this.config = new BarcodeConfig();
    }

    public BarcodeData(String content, BarcodeType type, BarcodeConfig config) {
        this.content = content;
        this.type = type;
        this.config = config;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BarcodeType getType() {
        return type;
    }

    public void setType(BarcodeType type) {
        this.type = type;
    }

    public BarcodeConfig getConfig() {
        return config;
    }

    public void setConfig(BarcodeConfig config) {
        this.config = config;
    }
}