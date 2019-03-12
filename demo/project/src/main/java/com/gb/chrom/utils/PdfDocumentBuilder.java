package com.gb.chrom.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gb.chrom.model.SlideTemplateItem;
import com.gb.chrom.utils.barcode.QRCode;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;

/**
 * @author Summer
 *
 *         2018年8月23日
 */
public class PdfDocumentBuilder {

	private static Logger logger = LoggerFactory.getLogger(PdfDocumentBuilder.class);

	public static final int PRINT_DPI = 300;
	public static final float CR = 72f / 25.4f;

	static FontFactoryImp fontFactory;
	static PdfDocumentBuilder barcodeInstance;

	static {
		fontFactory = new FontFactoryImp();
		
		if (StringUtils.containsIgnoreCase(System.getProperties().getProperty("os.name"), "windows")) {
			fontFactory.register(System.getenv().get("SystemRoot") + "/Fonts/msyh.ttc");
			// fontFactory.register(System.getenv().get("SystemRoot") + "/Fonts/msyhbd.ttf");
		} else {
			fontFactory.registerDirectory("/usr/local/Fonts");
		}
	}
	
	private static BaseFont getBaseFont() throws Exception {
		if (fontFactory.getRegisteredFonts().contains("微软雅黑")) { // 微软雅黑 bold
			Font font = fontFactory.getFont("微软雅黑", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			return font.getBaseFont();
		} else {
			return BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		}
	}

	public static class QRPdfDocumentBuilder {

		static final PdfDocumentBuilder INSTANCE = new PdfDocumentBuilder();
		static final float WIDTH = 25 * CR;
		static final float HEIGHT = 20 * CR;

		static Document document = null;
		static Rectangle rectangle = null;

		static {
			rectangle = new Rectangle(WIDTH, HEIGHT);
			document = new Document(rectangle, 0, 0, 0, 0);
		}

		public static byte[] builder(Map<String, SlideTemplateItem> itemMap, float pixelWidth) throws Exception {
			ByteArrayOutputStream output = null;

			try {
				output = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, output);

				float scale = WIDTH / pixelWidth;

				document.open();
				// document.newPage();

				PdfContentByte content = writer.getDirectContent();
				content.beginText();
				content.setColorFill(BaseColor.BLACK);

				SlideTemplateItem item = null;

				if (itemMap.containsKey("qr")) {
					item = itemMap.get("qr");
					float x = item.getPointX() * scale;
					float y = rectangle.getHeight() - (item.getPointY() + item.getHeight()) * scale;
					float w = item.getWidth() * scale;
					float h = item.getHeight() * scale;
					int qrSize = (int) Math.max(item.getWidth(), item.getHeight());
					writeQR(content, item.getValue(), x, y, w, h, qrSize);
				}

				Iterator<String> iterator = itemMap.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					if (StringUtils.equals("qr", key)) {
						continue;
					}

					item = itemMap.get(key);
					float x = item.getPointX() * scale;
					float y = rectangle.getHeight() - (item.getPointY() + item.getFontSize()) * scale;

					content.setFontAndSize(getBaseFont(), item.getFontSize() * scale);
					content.showTextAligned(PdfContentByte.ALIGN_LEFT, item.getValue(), x, y, 0);
				}

				content.endText();
				document.close();

			} catch (Exception e) {
				logger.error("Builder QR PDF document failed.", e);
			} finally {
				if (null != output) {
					output.close();
				}
			}

			return output.toByteArray();
		}
	}

	public static class BarcodePdfDocumentBuilder {
		static final PdfDocumentBuilder INSTANCE = new PdfDocumentBuilder();
		static final float WIDTH = 50 * CR;
		static final float HEIGHT = 25 * CR;

		static Document document = null;
		static Rectangle rectangle = null;

		static {
			rectangle = new Rectangle(WIDTH, HEIGHT);
			document = new Document(rectangle, 0, 0, 0, 0);
		}

		public static byte[] builder(String name, String code) throws Exception {
			ByteArrayOutputStream output = null;

			try {
				output = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, output);
				document.open();

				PdfContentByte content = writer.getDirectContent();
				content.beginText();
				content.setColorFill(BaseColor.BLACK);

				content.setFontAndSize(getBaseFont(), 10);
				content.showTextAligned(PdfContentByte.ALIGN_LEFT, name, 5, HEIGHT - 15, 0);
				
				Map<EncodeHintType, Object> hints = new HashMap<>();
				hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

				Barcode39 code39 = new Barcode39();
				code39.setCode(code);
				code39.setBarHeight(30);
				code39.setSize(12);
				code39.setN(3);
				code39.setX(1);
//				code39.setAltText(code);
				code39.setBaseline(2);

				Font font = fontFactory.getFont("宋体", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, Font.BOLD);
				font.setColor(CMYKColor.BLACK);
				code39.setFont(font.getBaseFont());
				//code39.setGuardBars(true);
				code39.setStartStopText(true);
				
				Image image = code39.createImageWithBarcode(content, BaseColor.BLACK, BaseColor.BLACK);
				image.setDpi(PRINT_DPI, PRINT_DPI);
				image.scaleAbsolute(rectangle.getWidth() - 10, rectangle.getHeight() - 46);
				image.setAbsolutePosition(5, 23);
				content.addImage(image);
				
				content.setFontAndSize(getBaseFont(), 8);
				content.showTextAligned(PdfContentByte.ALIGN_LEFT, code, 5, 14, 0);

				content.endText();
				document.close();
			} catch (Exception e) {
				logger.error("Builder QR PDF document failed.", e);
			} finally {
				if (null != output) {
					output.close();
				}
			}

			return output.toByteArray();
		}
	}

	public static void writeQR(PdfContentByte content, String data, float x, float y, float w, float h, int size) throws Exception {
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

		QRCode qrCode = new QRCode(data, size, size, hints);
		Image img = qrCode.getImage();
		img.scaleAbsolute(w, h);
		img.setAbsolutePosition(x, y);
		img.setBackgroundColor(BaseColor.WHITE);
		img.setDpi(PRINT_DPI, PRINT_DPI);
		img.setAlignment(Image.ALIGN_CENTER);
		content.addImage(img);
	}

	public static void main(String[] args) throws Exception {
		try (FileOutputStream outputStream = new FileOutputStream("d:/ttest.pdf")) {
			outputStream.write(BarcodePdfDocumentBuilder.builder("张三丰", "B20180001B01"));
		}
	}

}
