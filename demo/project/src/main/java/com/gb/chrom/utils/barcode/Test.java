package com.gb.chrom.utils.barcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;

/**
 * @author Summer
 *
 * 		2018年8月24日
 */
public class Test {

	
	public static void main(String[] args) throws BadElementException, FileNotFoundException, IOException {
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		QRCode qrCode = new QRCode("T201800004B03T201800004B03T201800004B03", 250, 250, hints);
//		Image image = qrCode.getImage();
//		image.setAlignment(Image.ALIGN_CENTER);
//		image.setDpi(300, 300);
//		image.setScaleToFitHeight(true);
//		image.setScaleToFitLineWhenOverflow(true);
//		image.scaleAbsolute(w + mend, h + mend);
//		image.setAbsolutePosition(x - mend / 2, y - mend / 2);
		Image image = qrCode.createAwtImage(Color.BLACK, Color.WHITE);
		
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        
        ImageIO.write(bi, "png", new FileOutputStream("d:/qrcode.png"));
	}
	
}
