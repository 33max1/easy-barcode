package com.easybarcode.util;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;

import java.awt.Color;
import java.io.IOException;

/**
 * 条码工具类（增强中文支持）
 */
public class BarcodeUtil {

    // 默认中文字体（使用系统字体或资源字体）
    private static PdfFont defaultChineseFont;

    static {
        try {
            // 尝试加载系统字体（Windows下通常是SimSun）
            defaultChineseFont = PdfFontFactory.createFont(
                    BarcodeUtil.class.getClassLoader().getResource("fonts/SourceHanSerifCN-Regular.ttf").getPath(),
                    PdfEncodings.IDENTITY_H,
                    EmbeddingStrategy.PREFER_EMBEDDED
            );
        } catch (Exception e) {
            try {
                // 如果系统字体不可用，尝试从资源加载字体（需要将字体文件放入resources/fonts目录）
                defaultChineseFont = PdfFontFactory.createFont(
                        BarcodeUtil.class.getClassLoader().getResource("fonts/simsun.ttf").getPath(),
                        PdfEncodings.IDENTITY_H,
                        EmbeddingStrategy.PREFER_EMBEDDED
                );
            } catch (IOException ex) {
                throw new RuntimeException("无法加载中文字体", ex);
            }
        }
    }

    /**
     * 获取默认中文字体
     */
    public static PdfFont getDefaultChineseFont() {
        return defaultChineseFont;
    }

    /**
     * 将AWT颜色转换为iText颜色
     */
    public static DeviceRgb awtToPdfColor(Color color) {
        return new DeviceRgb(color.getRed(), color.getGreen(), color.getBlue());
    }
}