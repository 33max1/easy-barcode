package com.easybarcode.util;

import com.itextpdf.kernel.colors.DeviceRgb;
import java.awt.*;

/**
 * 条码工具类
 */
public class BarcodeUtil {

    /**
     * 将AWT颜色转换为iText颜色
     * @param color AWT颜色
     * @return iText颜色
     */
    public static DeviceRgb awtToPdfColor(Color color) {
        return new DeviceRgb(color.getRed(), color.getGreen(), color.getBlue());
    }
}