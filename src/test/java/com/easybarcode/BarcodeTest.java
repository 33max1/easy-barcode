package com.easybarcode;

import com.easybarcode.core.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarcodeTest {

    @Test
    public void testEnhancedBarcode() throws IOException {
        // 创建条码数据
        BarcodeData data = new BarcodeData(
                "PROD-2023-001-002",
                BarcodeType.CODE_128,
                new BarcodeConfig()
                        .setWidth(300)
                        .setHeight(50)
                        .setTitle("产品条码") // 条码上方标题
                        .setTitleSize(16)
                        .setTitleColor(new Color(0, 0, 139)) // 深蓝色标题
                        .setTextLines(Arrays.asList(
                                "产品编号: PROD-2023-001-002",
                                "生产日期: 2023-05-15",
                                "有效期至: 2024-05-14"
                        )) // 多行文本
                        .setTextColor(Color.DARK_GRAY)
                        .setTextSize(10)
                        .setMarginTop(15) // 上边距
                        .setMarginBottom(15) // 下边距
                        .setTextAlignment(TextAlignment.LEFT) // 左对齐
        );

        // 导出到PDF
        BarcodeExporter.exportToPdf("enhanced_barcode.pdf", data);
    }

    @Test
    public void testMultipleEnhancedBarcodes() throws IOException {
        List<BarcodeData> barcodeList = new ArrayList<>();

        // 第一个条码
        barcodeList.add(new BarcodeData(
                "ISBN-978-3-16-148410-0",
                BarcodeType.EAN_13,
                new BarcodeConfig()
                        .setWidth(300)
                        .setHeight(120)
                        .setTitle("图书ISBN")
                        .setTitleColor(new Color(139, 0, 0)) // 深红色
                        .setTextLines(Arrays.asList(
                                "ISBN: 978-3-16-148410-0",
                                "书名: 深入理解Java虚拟机",
                                "作者: 周志明"
                        ))
                        .setTextAlignment(TextAlignment.CENTER)
        ));

        // 第二个条码
        barcodeList.add(new BarcodeData(
                "https://www.company.com/product/123",
                BarcodeType.QR_CODE,
                new BarcodeConfig()
                        .setWidth(150)
                        .setHeight(150)
                        .setTitle("产品详情")
                        .setTitleSize(14)
                        .setTextLines(Arrays.asList(
                                "扫描二维码查看产品详情",
                                "或访问: www.company.com"
                        ))
                        .setMarginLeft(50) // 左边距
                        .setMarginRight(50) // 右边距
        ));

        // 第三个条码（右对齐）
        barcodeList.add(new BarcodeData(
                "WH-2023-05-001",
                BarcodeType.CODE_39,
                new BarcodeConfig()
                        .setWidth(400)
                        .setHeight(80)
                        .setTitle("仓库管理")
                        .setTitleColor(new Color(0, 100, 0)) // 深绿色
                        .setTextLines(Arrays.asList(
                                "仓库编号: WH-001",
                                "入库单号: WH-2023-05-001",
                                "入库日期: 2023-05-15"
                        ))
                        .setTextAlignment(TextAlignment.RIGHT)
        ));

        // 导出到PDF
        BarcodeExporter.exportToPdf("multiple_enhanced_barcodes.pdf", barcodeList);
    }
}