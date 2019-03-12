package com.gb.chrom.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gb.chrom.web.result.JsonResult;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 文件传输
 * 
 * @author Summer
 *
 *         2016年5月27日
 */
@RestController
@RequestMapping("/transfer")
public class FileTransferController {

	static final Logger logger = LoggerFactory.getLogger(FileTransferController.class);

	static final String SEPARATOR = "/";
	static final String THUMMB_SUFFIX = "_thumb.";

	static final String IMAGE_MEDIA = "/images";
	static final String ATTACHMENTS = "/attachments";
	static final String TECHNICAL_DOCUMENT = "/documents";

	@Autowired
	private MultipartConfigElement multipartConfigElement;

	/**
	 * Upload image file
	 */
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public JsonResult imageUpload(@RequestParam(value = "file") MultipartFile file, boolean thumb) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();

			String originname = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originname).toLowerCase();

			String path = getDestFilePath(file, IMAGE_MEDIA, extension);
			File dest = new File(multipartConfigElement.getLocation() + path);
			FileUtils.copyInputStreamToFile(file.getInputStream(), dest);

			data.put("file", path);
			data.put("filename", originname);
			data.put("type", "image");

			if (thumb) {
				String thumbName = new StringBuilder(FilenameUtils.getFullPath(path)).append(FilenameUtils.getBaseName(path)).append(THUMMB_SUFFIX)
						.append(extension).toString();
				File destThumb = new File(multipartConfigElement.getLocation() + thumbName);

				Thumbnails.of(dest).size(960, 960).toFile(destThumb);
				data.put("thumb", thumbName);
			}

			return JsonResult.success(data);
		} catch (Exception e) {
			logger.error("Upload image failed: ", e);
		}

		return JsonResult.error("图片上传失败!");
	}

	/**
	 * Upload image file
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public JsonResult fileUpload(@RequestParam(value = "file") MultipartFile file) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();

			String originname = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originname).toLowerCase();

			String path = getDestFilePath(file, ATTACHMENTS, extension);
			File dest = new File(multipartConfigElement.getLocation() + path);
			FileUtils.copyInputStreamToFile(file.getInputStream(), dest);

			data.put("file", path);
			data.put("filename", originname);
			data.put("type", FilenameUtils.getExtension(originname));
			data.put("length", dest.length() / 1024);

			return JsonResult.success(data);
		} catch (Exception e) {
			logger.error("Upload file failed: ", e);
		}

		return JsonResult.error("文件上传失败!");
	}

	/**
	 * Upload image file
	 */
	@RequestMapping(value = "/document/upload", method = RequestMethod.POST)
	public JsonResult documentUpload(@RequestParam(value = "file") MultipartFile file) {
		try {
			Map<String, Object> data = new HashMap<String, Object>();

			String originname = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(originname).toLowerCase();

			String path = getDestFilePath(file, TECHNICAL_DOCUMENT, extension);
			File dest = new File(multipartConfigElement.getLocation() + path);
			FileUtils.copyInputStreamToFile(file.getInputStream(), dest);

			data.put("file", path);
			data.put("filename", originname);
			data.put("type", FilenameUtils.getExtension(originname));
			data.put("length", dest.length() / 1024);

			return JsonResult.success(data);
		} catch (Exception e) {
			logger.error("Upload file failed: ", e);
		}

		return JsonResult.error("文件上传失败!");
	}

	@RequestMapping(value = "/file/download")
	public void downloadFile(String file, String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");

		File loadFile = new File(multipartConfigElement.getLocation(), file);
		long fileLength = loadFile.length();
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-disposition", "attachment; filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(loadFile));
				BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = input.read(buff, 0, buff.length))) {
				output.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			logger.error("Exception occurred during download file: {}", filename, e);
		}
	}

	@RequestMapping(value = "/pdf/print-stream")
	public void getPDFOutputStream(@RequestParam("file") String filename, HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		OutputStream output = response.getOutputStream();
		BufferedInputStream buffered = new BufferedInputStream(new FileInputStream(new File(multipartConfigElement.getLocation(), filename)));
		FileCopyUtils.copy(buffered, output);
	}

	private String getDestFilePath(MultipartFile file, String dir, String ext) throws IOException {
		String md5Str = DigestUtils.md5Hex(file.getInputStream());

		StringBuffer buffer = new StringBuffer();
		buffer.append(dir).append(SEPARATOR);
		buffer.append(DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy"));
		buffer.append(SEPARATOR);
		buffer.append(md5Str.substring(0, 8));
		buffer.append(SEPARATOR);
		buffer.append(md5Str.substring(8, 16));
		buffer.append(SEPARATOR);
		buffer.append(md5Str.substring(16));

		if (StringUtils.isNoneBlank(ext)) {
			buffer.append(".").append(ext);
		}

		return buffer.toString();
	}

}
