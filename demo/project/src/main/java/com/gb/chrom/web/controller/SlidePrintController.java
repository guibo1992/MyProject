package com.gb.chrom.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.SlidePrintRecordsService;
import com.gb.chrom.core.service.SlideTemplateService;
import com.gb.chrom.core.service.SpecimenService;
import com.gb.chrom.core.service.SpecimenTypeService;
import com.gb.chrom.model.Configuration;
import com.gb.chrom.model.Patient;
import com.gb.chrom.model.SlidePrintRecords;
import com.gb.chrom.model.SlideTemplate;
import com.gb.chrom.model.SlideTemplateItem;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.query.SlidePrintRecordsQuery;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.model.query.SpecimenTypeQuery;
import com.gb.chrom.utils.DocumenPrinter;
import com.gb.chrom.utils.PdfDocumentBuilder;
import com.gb.chrom.web.result.JsonResult;
import com.gb.chrom.web.result.PaginatorJsonResult;

/**
 * @since 1.0
 * @author Summer
 */
@Controller
@RequestMapping(value = "/slide/print")
public class SlidePrintController {

	@Autowired
	private SpecimenService specimenService;
	@Autowired
	private SpecimenTypeService specimenTypeService;

	@Autowired
	private SlideTemplateService slideTemplateService;
	@Autowired
	private SlidePrintRecordsService slidePrintRecordsService;

	@Autowired
	private ConfigurationService configurationService;

	@RequestMapping(value = "/fast-list.html")
	public String fastPrint(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/cyto/slide-print-list";
	}

	@RequestMapping(value = "/records-list.html")
	public String specimenPringRecords(Model model) {
		model.addAttribute("typeList", specimenTypeService.querySpecimenTypeForList(new SpecimenTypeQuery(true)));
		return "view/cyto/slide-print-records";
	}

	@RequestMapping(value = "/list")
	public @ResponseBody PaginatorJsonResult specimenPrintList(SpecimenQuery query) {
		return PaginatorJsonResult.getPaginatorResult(specimenService.querySpecimenForPagingList(query));
	}

	@RequestMapping(value = "/records/list")
	public @ResponseBody PaginatorJsonResult specimenPrintRecordsList(SlidePrintRecordsQuery query) {
		return PaginatorJsonResult.getPaginatorResult(slidePrintRecordsService.querySlidePrintRecordsForPagingList(query));
	}

	@RequestMapping(value = "/{id}/print")
	public @ResponseBody JsonResult print(@PathVariable("id") long id) {
		Specimen specimen = specimenService.querySpecimen(id);
		SlideTemplate template = slideTemplateService.querySlideTemplate();

		if (null == template) {
			return JsonResult.error("未设置的标本打印模板!");
		}

		Configuration config = configurationService.getConfiguration();

		int count = 0;
		Map<String, SlideTemplateItem> itemMap = template.getItemMap();

		if (itemMap.containsKey("pn")) {
			Patient patient = specimen.getPatient();
			itemMap.get("pn").setValue(null == patient || null == patient.getName() ? "" : patient.getName());
		}
		if (itemMap.containsKey("date")) {
			itemMap.get("date").setValue(DateFormatUtils.format(specimen.getGmtCreate(), "MM/dd/yyyy"));
		}
		try {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < specimen.getLinePrintCount(); i++) {
				builder.setLength(0);
				builder.append(specimen.getSpecimenNo()).append("A").append(i + 1);
				itemMap.get("sn").setValue(builder.toString());
				itemMap.get("qr").setValue(builder.toString());

				if (StringUtils.isNotBlank(config.getSlidePrinter())) {
					byte[] bytes = PdfDocumentBuilder.QRPdfDocumentBuilder.builder(template.getItemMap(), template.getWidth());
					DocumenPrinter.printSpecimenDocumentBytes(bytes, config.getSlidePrinter());
				}
				count++;

				if (StringUtils.containsIgnoreCase(specimen.getLineType(), "B")) {
					builder.setLength(0);
					builder.append(specimen.getSpecimenNo()).append("B").append(i + 1);

					itemMap.get("sn").setValue(builder.toString());
					itemMap.get("qr").setValue(builder.toString());
					
					if (StringUtils.isNotBlank(config.getSlidePrinter())) {
						byte[]bytes = PdfDocumentBuilder.QRPdfDocumentBuilder.builder(template.getItemMap(), template.getWidth());
						DocumenPrinter.printSpecimenDocumentBytes(bytes, config.getSlidePrinter());
					}
					count++;
				}
			}
		} catch (Exception e) {
			return JsonResult.error("载玻片打印失败!");
		}
		specimenPrintRecords(specimen);

		return JsonResult.success(count);
	}

	@RequestMapping(value = "/batch")
	public @ResponseBody JsonResult print(@RequestParam("ids[]") Long[] array) {
		if (ArrayUtils.isEmpty(array)) {
			return JsonResult.error("无效的参数提交!");
		}

		List<Long> idList = Arrays.asList(array);
		SpecimenQuery query = new SpecimenQuery();
		query.setCount(false);
		query.setLimit(Integer.MAX_VALUE);
		query.setIdList(idList);
		List<Specimen> list = specimenService.querySpecimenForPagingList(query).getList();

		int total = 0;
		for (Specimen sp : list) {
			total += (StringUtils.containsIgnoreCase(sp.getLineType(), "B") ? 2 : 1) * sp.getLinePrintCount();

			if (total > 100) {
				return JsonResult.error("打印载玻片总数已超出范围(1-100)!");
			}
		}
		try {
			Configuration config = configurationService.getConfiguration();
			SlideTemplate template = slideTemplateService.querySlideTemplate();

			for (Specimen spe : list) {
				Map<String, SlideTemplateItem> itemMap = template.getItemMap();

				if (itemMap.containsKey("pn")) {
					Patient patient = spe.getPatient();
					itemMap.get("pn").setValue(null == patient || null == patient.getName() ? "" : patient.getName());
				}
				if (itemMap.containsKey("date")) {
					itemMap.get("date").setValue(DateFormatUtils.format(spe.getGmtCreate(), "MM/dd/yyyy"));
				}

				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < spe.getLinePrintCount(); i++) {
					builder.setLength(0);
					builder.append(spe.getSpecimenNo()).append("A").append(i + 1);
					itemMap.get("sn").setValue(builder.toString());
					itemMap.get("qr").setValue(builder.toString());

					if (StringUtils.isNotBlank(config.getSlidePrinter())) {
						byte[] bytes = PdfDocumentBuilder.QRPdfDocumentBuilder.builder(template.getItemMap(), template.getWidth());
						DocumenPrinter.printSpecimenDocumentBytes(bytes, config.getSlidePrinter());
					}

					if (StringUtils.containsIgnoreCase(spe.getLineType(), "B")) {
						builder.setLength(0);
						builder.append(spe.getSpecimenNo()).append("B").append(i + 1);

						itemMap.get("sn").setValue(builder.toString());
						itemMap.get("qr").setValue(builder.toString());

						if (StringUtils.isNotBlank(config.getSlidePrinter())) {
							byte[] bytes = PdfDocumentBuilder.QRPdfDocumentBuilder.builder(template.getItemMap(), template.getWidth());
							DocumenPrinter.printSpecimenDocumentBytes(bytes, config.getSlidePrinter());
						}
					}
				}

				specimenPrintRecords(spe);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.error("载玻片批量打印失败!");
		}

		return JsonResult.success(total);
	}

	private void specimenPrintRecords(Specimen specimen) {
		SlidePrintRecords records = new SlidePrintRecords();
		records.setHisId(specimen.getHisId());
		records.setSpecimenId(specimen.getId());
		records.setSpecimenNo(specimen.getSpecimenNo());
		records.setTypeId(specimen.getTypeId());
		records.setPrintCount(specimen.getLinePrintCount());
		records.setPrintTime(Calendar.getInstance().getTime());

		slidePrintRecordsService.addSlidePrintRecords(records);
	}

}
