package com.easybarcode.core;

import com.easybarcode.exception.BarcodeExportException;
import com.easybarcode.util.BarcodeUtil;
import com.google.zxing.WriterException;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 条码PDF导出器（增强版）
 */
public class BarcodeExporter {
    // 添加布局相关常量
    private static final float TOTAL_WIDTH = 520f; // PDF页面总宽度
    private static final float COLUMN_WIDTH = TOTAL_WIDTH / 8f; // 每栏宽度

    /**
     * 导出单个条码到PDF文件
     * @param filePath 输出文件路径
     * @param data 条码数据
     * @throws IOException 如果文件操作失败
     */
    public static void exportToPdf(String filePath, BarcodeData data) throws IOException {
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            addBarcodeToDocument(document, data);
        } catch (Exception e) {
            throw new BarcodeExportException("Failed to export barcode to PDF", e);
        }
    }

    /**
     * 导出多个条码到单个PDF文件
     * @param filePath 输出文件路径
     * @param dataList 条码数据列表
     * @throws IOException 如果文件操作失败
     */
    public static void exportToPdf(String filePath, List<BarcodeData> dataList) throws IOException {
        try (PdfWriter writer = new PdfWriter(filePath);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            for (BarcodeData data : dataList) {
                addBarcodeToDocument(document, data);
                document.add(new Paragraph("\n")); // 添加间隔
            }
        } catch (Exception e) {
            throw new BarcodeExportException("Failed to export barcodes to PDF", e);
        }
    }

    private static void addBarcodeToDocument(Document document, BarcodeData data) throws IOException {
        try {
            BarcodeConfig config = data.getConfig();
            BarcodeLayout layout = config.getLayout();

            // 创建容器div
            // 创建容器div
            Div container = new Div();

            // 设置布局
            float width = COLUMN_WIDTH * layout.getSpan();
            float offset = COLUMN_WIDTH * layout.getOffset();

            container.setWidth(width)
                    .setMarginLeft(offset)
                    .setMarginTop(layout.isNewRow() ? 10f : 0f)
                    .setMarginBottom(10f);

            // 添加标题（如果存在）
            if (config.getTitle() != null && !config.getTitle().isEmpty()) {
                Paragraph title = new Paragraph(config.getTitle())
                        .setFont(BarcodeUtil.getDefaultChineseFont()) // 使用中文字体
                        .setFontColor(BarcodeUtil.awtToPdfColor(config.getTitleColor()))
                        .setFontSize(config.getTitleSize())
                        .setTextAlignment(config.getTextAlignment())
                        .setMarginBottom(5f);
                container.add(title);
            }

            // 生成条码图像
            BufferedImage barcodeImage = BarcodeBuilder.buildBarcodeImage(data);

            // 转换为PDF可用的图像
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(barcodeImage, "png", baos);
            ImageData imageData = ImageDataFactory.create(baos.toByteArray());
            Image pdfImage = new Image(imageData);

            // 设置图像对齐
            pdfImage.setHorizontalAlignment(convertAlignment(config.getTextAlignment()));

            // 添加到容器
            container.add(pdfImage);

            // 添加多行文本（如果存在）
            if (config.isShowText() && config.getTextLines() != null && !config.getTextLines().isEmpty()) {
                for (String text : config.getTextLines()) {
                    Paragraph textParagraph = new Paragraph(text)
                            .setFont(BarcodeUtil.getDefaultChineseFont()) // 使用中文字体
                            .setFontColor(BarcodeUtil.awtToPdfColor(config.getTextColor()))
                            .setFontSize(config.getTextSize())
                            .setTextAlignment(config.getTextAlignment())
                            .setMarginTop(2f);
                    container.add(textParagraph);
                }
            }

            // 将容器添加到文档
            document.add(container);

        } catch (WriterException e) {
            throw new BarcodeExportException("Failed to generate barcode image", e);
        }
    }

    private static HorizontalAlignment convertAlignment(TextAlignment alignment) {
        switch (alignment) {
            case LEFT: return HorizontalAlignment.LEFT;
            case RIGHT: return HorizontalAlignment.RIGHT;
            case CENTER:
            default: return HorizontalAlignment.CENTER;
        }
    }
}