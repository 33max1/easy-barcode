package com.easybarcode.core;

import com.itextpdf.layout.properties.TextAlignment;

import java.awt.Color;
import java.util.List;

/**
 * 条码配置类
 */
public class BarcodeConfig {
    private int width = 300;
    private int height = 100;
    private Color foregroundColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private List<String> textLines; // 多行文本（条码下方）
    private boolean showText = true; // 是否显示文本
    private float textSize = 12f; // 文字大小
    private Color textColor = Color.BLACK; // 文字颜色
    private String title; // 条码上方标题
    private float titleSize = 14f; // 标题大小
    private Color titleColor = Color.BLACK; // 标题颜色
    private float marginTop = 10f; // 上边距
    private float marginBottom = 10f; // 下边距
    private float marginLeft = 10f; // 左边距
    private float marginRight = 10f; // 右边距
    private TextAlignment textAlignment = TextAlignment.CENTER; // 文本对齐方式

    // 构造函数、getter和setter方法

    public BarcodeConfig() {
    }

    public BarcodeConfig(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public List<String> getTextLines() {
        return textLines;
    }

    public BarcodeConfig setTextLines(List<String> textLines) {
        this.textLines = textLines;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BarcodeConfig setTitle(String title) {
        this.title = title;
        return this;
    }

    public float getTitleSize() {
        return titleSize;
    }

    public BarcodeConfig setTitleSize(float titleSize) {
        this.titleSize = titleSize;
        return this;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public BarcodeConfig setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public BarcodeConfig setMarginTop(float marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public BarcodeConfig setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public BarcodeConfig setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public BarcodeConfig setMarginRight(float marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    public BarcodeConfig setTextAlignment(TextAlignment textAlignment) {
        this.textAlignment = textAlignment;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public BarcodeConfig setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public BarcodeConfig setHeight(int height) {
        this.height = height;
        return this;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public BarcodeConfig setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        return this;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public BarcodeConfig setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public boolean isShowText() {
        return showText;
    }

    public BarcodeConfig setShowText(boolean showText) {
        this.showText = showText;
        return this;
    }

    public float getTextSize() {
        return textSize;
    }

    public BarcodeConfig setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Color getTextColor() {
        return textColor;
    }

    public BarcodeConfig setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }
}