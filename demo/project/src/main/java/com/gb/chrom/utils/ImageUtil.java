package com.gb.chrom.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Summer
 *
 * 		2018年9月10日
 */
public class ImageUtil {
	
	private static final String MASK_FILENAME_SUFFIX = "_mask";
	
	public static String maskImage(String location, String filename) throws Exception {
		if (StringUtils.isBlank(filename)) {
			return null;
		}
		try {
			File srcFile = new File(location, filename);
			Image image = ImageIO.read(new FileInputStream(srcFile));
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			int maskWidth = (15 * width) / 100;
			int maskHeight = (25 * height) / 100;

			BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bimage.createGraphics();
			g2d.drawImage(image, 0, 0, null);
			g2d.setColor(Color.WHITE);
			g2d.fillRect(width - maskWidth, height - maskHeight - 10, maskWidth, maskHeight);
			g2d.dispose();

			String path = FilenameUtils.getFullPath(filename);
			String name = FilenameUtils.getBaseName(filename);
			String extension = FilenameUtils.getExtension(filename);
			
			StringBuilder builder = new StringBuilder(path);
			builder.append(name).append(MASK_FILENAME_SUFFIX).append(".").append(extension);

			try (FileOutputStream output = new FileOutputStream(new File(location, builder.toString()))) {
				ImageIO.write(bimage, extension, output);
			}
			bimage.flush();

			return builder.toString();
		} catch (Exception e) {
			throw e;
		} 
	}

}
