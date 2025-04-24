package com.easybarcode.core;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.aztec.AztecWriter;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * 条码构建器，使用ZXing生成条码图像
 */
public class BarcodeBuilder {

    /**
     * 根据条码数据生成条码图像
     * @param data 条码数据
     * @return 生成的条码图像
     * @throws WriterException 如果生成条码失败
     */
    public static BufferedImage buildBarcodeImage(BarcodeData data) throws WriterException {
        String content = data.getContent();
        BarcodeType type = data.getType();
        BarcodeConfig config = data.getConfig();

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Barcode content cannot be empty");
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix;
        int width = config.getWidth();
        int height = config.getHeight();

        switch (type) {
            case QR_CODE:
                bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                break;
            case CODE_128:
                bitMatrix = new Code128Writer().encode(content, BarcodeFormat.CODE_128, width, height, hints);
                break;
            case EAN_13:
                bitMatrix = new EAN13Writer().encode(content, BarcodeFormat.EAN_13, width, height, hints);
                break;
            case UPC_A:
                bitMatrix = new UPCAWriter().encode(content, BarcodeFormat.UPC_A, width, height, hints);
                break;
            case CODE_39:
                bitMatrix = new Code39Writer().encode(content, BarcodeFormat.CODE_39, width, height, hints);
                break;
            case ITF:
                bitMatrix = new ITFWriter().encode(content, BarcodeFormat.ITF, width, height, hints);
                break;
            case PDF_417:
                bitMatrix = new PDF417Writer().encode(content, BarcodeFormat.PDF_417, width, height, hints);
                break;
            case DATA_MATRIX:
                bitMatrix = new DataMatrixWriter().encode(content, BarcodeFormat.DATA_MATRIX, width, height, hints);
                break;
            case AZTEC:
                bitMatrix = new AztecWriter().encode(content, BarcodeFormat.AZTEC, width, height, hints);
                break;
            default:
                throw new IllegalArgumentException("Unsupported barcode type: " + type);
        }

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}