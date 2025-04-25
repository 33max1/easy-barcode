package com.easybarcode.core;

/**
 * 条码布局配置类（基于8分栏系统）
 */
public class BarcodeLayout {
    private int span = 8; // 占据的栏数（1-8）
    private int offset = 0; // 偏移栏数（0-7）
    private boolean newRow = false; // 是否在新行开始

    public BarcodeLayout() {
    }

    public BarcodeLayout(int span) {
        this.span = span;
    }

    public BarcodeLayout(int span, int offset) {
        this.span = span;
        this.offset = offset;
    }

    public BarcodeLayout(int span, int offset, boolean newRow) {
        this.span = span;
        this.offset = offset;
        this.newRow = newRow;
    }

    // 校验方法
    private void validate() {
        if (span < 1 || span > 8) {
            throw new IllegalArgumentException("Span must be between 1 and 8");
        }
        if (offset < 0 || offset > 7) {
            throw new IllegalArgumentException("Offset must be between 0 and 7");
        }
        if (span + offset > 8) {
            throw new IllegalArgumentException("Span + offset cannot exceed 8");
        }
    }

    // Getters and Setters...
    public int getSpan() {
        return span;
    }

    public BarcodeLayout setSpan(int span) {
        this.span = span;
        validate();
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public BarcodeLayout setOffset(int offset) {
        this.offset = offset;
        validate();
        return this;
    }

    public boolean isNewRow() {
        return newRow;
    }

    public BarcodeLayout setNewRow(boolean newRow) {
        this.newRow = newRow;
        return this;
    }
}