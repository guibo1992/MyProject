package com.gb.chrom.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.SlideTemplateService;
import com.gb.chrom.model.SlideTemplate;
import com.gb.chrom.model.SlideTemplateItem;
import com.gb.chrom.utils.PdfDocumentBuilder;
import com.gb.chrom.web.result.JsonResult;

/**
 * Specimen number rule
 * 
 * @author Summer
 */
@Controller
@RequestMapping(value = "/slide")
public class SlideTemplateController {

	private static final Logger logger = LoggerFactory.getLogger(SlideTemplateController.class);

	@Autowired
	private SlideTemplateService slideTemplateService;
	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@RequestMapping(value = "/template.html")
	public String slideTemplate(HttpServletRequest request, Model model) {
		SlideTemplate template = slideTemplateService.querySlideTemplate();
		if (null == template) {
			template = new SlideTemplate();
			template.setName(UUID.randomUUID().toString());
		}
		model.addAttribute("template", template);

		return "view/slide-template";
	}

	@RequestMapping(value = "/template/save")
	public @ResponseBody JsonResult saveTemplate(@RequestBody SlideTemplate template) {
		SlideTemplate tmpl = slideTemplateService.querySlideTemplate();

		boolean result = true;
		if (null == tmpl) {
			result = slideTemplateService.saveSlideTemplate(template);
		} else {
			template.setId(tmpl.getId());
			result = slideTemplateService.updateSlideTemplate(template);
		}

		return JsonResult.getJsonResult(result, "操作失败!");
	}

	@RequestMapping(value = "/template/print")
	public @ResponseBody JsonResult sampleSlideTemplate(@RequestBody SlideTemplate template, HttpServletResponse response) {
		if (CollectionUtils.isEmpty(template.getItemList())) {
			return JsonResult.error("无效的模板数据!");
		}
		try {
			Map<String, SlideTemplateItem> itemMap = new HashMap<>();
			for (SlideTemplateItem item : template.getItemList()) {
				itemMap.put(item.getType(), item);
			}
			itemMap.get("qr").setValue(itemMap.get("sn").getValue());
			template.setItemMap(itemMap);

			String filename = multipartConfigElement.getLocation() + "/examples/QR.pdf";
			File pdfFile = new File(filename);
			if (pdfFile.exists()) {
				pdfFile.delete();
			}
			if (!pdfFile.getParentFile().exists()) {
				pdfFile.getParentFile().mkdirs();
			}

			byte[] bytes = PdfDocumentBuilder.QRPdfDocumentBuilder.builder(itemMap, template.getWidth());

			try (FileOutputStream output = new FileOutputStream(filename)) {
				output.write(bytes);
			}

			return JsonResult.success(filename);
		} catch (Exception e) {
			logger.error("PDF file build failed :", e);
		}

		return JsonResult.error("操作失败!");
	}

	@RequestMapping(value = "/template/preview")
	public void getPdf(@RequestParam("file") String filename, HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		try (BufferedInputStream buffered = new BufferedInputStream(new FileInputStream(filename))) {
			FileCopyUtils.copy(buffered, response.getOutputStream());
		}
	}

}
