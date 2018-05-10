package com.souleater.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.souleater.init.Constants;
import com.souleater.oth.ImageSource;

/**
 * 二维码相关的工具类
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:16:38
 */
public class QRCodeUtils {

	private static Logger log = LogUtils.getLogger();
	
	/**
	 * 手动去除白边
	 * @param matrix
	 * @return
	 */
/*	private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }*/
	
	private static BufferedImage createImage(String content, String logoPath, boolean needCompress) {
		BufferedImage image = null;
		try {
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			hints.put(EncodeHintType.CHARACTER_SET, Constants.CHARSET);
			hints.put(EncodeHintType.MARGIN, BigInteger.ZERO);
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, Constants.QR_CODE_SIZE, Constants.QR_CODE_SIZE, hints);
			
			//bitMatrix = deleteWhite(bitMatrix);
			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				}
			}
			if (StringUtils.isBlank(logoPath)) {
				return image;
			}
			
			QRCodeUtils.insertImage(image, logoPath, needCompress);
		} catch (WriterException e) {
			e.printStackTrace();
		}

		return image;
	}

	private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) {
		try {
			File file = new File(logoPath);
			if (!file.exists()) {
				return;
			}
			Image src = ImageIO.read(file);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			if (needCompress) {
				if (width > Constants.QR_CODE_WIDTH) {
					width = Constants.QR_CODE_WIDTH;
				}
				if (height > Constants.QR_CODE_HEIGHT) {
					height = Constants.QR_CODE_HEIGHT;
				}
				Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(image, 0, 0, null);
				g.dispose();
				src = image;
			}
			
			Graphics2D graph = source.createGraphics();
			int x = (source.getWidth() - width) / 2;
			int y = (source.getHeight() - height) / 2;
			graph.drawImage(src, x, y, width, height, null);
			Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
			graph.setStroke(new BasicStroke(3f));
			graph.draw(shape);
			graph.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void encode(String content, String logoPath, String destPath, boolean needCompress) {
		try {
			log.info("开始创建二维码");
			BufferedImage image = QRCodeUtils.createImage(content, logoPath, needCompress);
			String filePath = FileUtils.createFile(destPath,Constants.QR_CODE_SUFFIX);
			ImageIO.write(image, Constants.QR_CODE_SUFFIX, new File(filePath));
			log.info("生成的文件路径：" + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("结束创建二维码");
		}
	}

	public static void encode(String content, String logoPath, String destPath) {
		QRCodeUtils.encode(content, logoPath, destPath, true);
	}

	public static void encode(String content, String destPath, boolean needCompress) {
		QRCodeUtils.encode(content, CommonUtils.getLogoPath(), destPath, needCompress);
	}

	public static void encode(String content, String destPath) throws Exception {
		QRCodeUtils.encode(content, CommonUtils.getLogoPath(), destPath, true);
	}

	public static void encode(String content, String logoPath,OutputStream output, boolean needCompress) {
		try {
			BufferedImage image = QRCodeUtils.createImage(content, logoPath, needCompress);
			ImageIO.write(image, Constants.QR_CODE_SUFFIX, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void encode(String content, OutputStream output) {
		QRCodeUtils.encode(content, CommonUtils.getLogoPath(), output, true);
	}
	
	public static void encode(String content) {
		QRCodeUtils.encode(content, CommonUtils.getLogoPath(), Constants.QR_CODE_PATH, true);
	}

	public static String decode(String path) throws Exception {
		return QRCodeUtils.decode(new File(path));
	}

	private static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		
		ImageSource source = new ImageSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, Constants.CHARSET);
		Result result = new MultiFormatReader().decode(bitmap, hints);
		return result.getText();
	}
}