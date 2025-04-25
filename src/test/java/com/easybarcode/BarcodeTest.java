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

    @Test
    public void testComplexLayout() throws IOException {
        List<BarcodeData> barcodeList = new ArrayList<>();

        // 标题行（占满8栏）
        barcodeList.add(createBarcodeData("esp-01","仓库管理系统", BarcodeType.CODE_39, 8, 0, true));

        // 第一行：1-3-4布局
        barcodeList.add(createBarcodeData("esp-01","区域A", BarcodeType.CODE_128, 1, 0, false));
        barcodeList.add(createBarcodeData("esp-02","货架B-05", BarcodeType.CODE_128, 3, 0, false));
        barcodeList.add(createBarcodeData("esp-03","2023-05-15", BarcodeType.CODE_128, 4, 0, false));

        // 第二行：偏移2栏，占4栏
        barcodeList.add(createBarcodeData("esp-05","扫描查询", BarcodeType.QR_CODE, 4, 2, true));

        // 导出到PDF
        BarcodeExporter.exportToPdf("complex_layout.pdf", barcodeList);
    }

    private BarcodeData createBarcodeData(String content, String text, BarcodeType type, int span, int offset, boolean newRow) {
        return new BarcodeData(
                content.replaceAll("\\s", "-"), // 用-替换空格作为条码内容
                type,
                new BarcodeConfig()
                        .setLayout(new BarcodeLayout(span, offset, newRow))
                        .setTextLines(Arrays.asList(text))
        );
    }
}