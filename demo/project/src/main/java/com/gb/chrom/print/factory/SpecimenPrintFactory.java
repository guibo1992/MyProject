package com.gb.chrom.print.factory;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gb.chrom.core.service.SlideTemplateService;
import com.gb.chrom.core.service.SlidePrintRecordsService;
import com.gb.chrom.model.SlideTemplate;
import com.gb.chrom.model.SlideTemplateItem;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SlidePrintRecords;

/**
 * @since 1.0
 * @author Summer
 */
@Component
public class SpecimenPrintFactory {

	private static final Logger logger = LoggerFactory.getLogger(SpecimenPrintFactory.class);

	@Autowired
	private MultipartConfigElement multipartConfigElement;

	@Autowired
	private SlideTemplateService printTemplateService;
	@Autowired
	private SlidePrintRecordsService specimenPrintRecordsService;

	public int batchPrintSpecimen(List<Specimen> specList) {
		int total = 0;
		if (CollectionUtils.isEmpty(specList)) {
			return total;
		}
		for (Specimen spec : specList) {
			total += printSpecimen(spec);
		}

		return total;
	}

	public int printSpecimen(Specimen specimen) {
		int count = 0;
		if (null == specimen) {
			return count;
		}
		SlideTemplate template = printTemplateService.querySlideTemplate();
		if (null == template) {
			return 0;
		}

		SlidePrintRecords records = new SlidePrintRecords();
		records.setHisId(specimen.getHisId());
		records.setSpecimenId(specimen.getId());
		records.setTypeId(specimen.getTypeId());
		records.setSpecimenNo(specimen.getSpecimenNo());
		records.setPrintTime(Calendar.getInstance().getTime());
		String printDate = DateFormatUtils.format(specimen.getGmtCreate(), "MM/dd/yyyy");

		for (int i = 0; i < specimen.getLinePrintCount(); i++) {
			if (StringUtils.equals("B", specimen.getLineType())) {
				String serialNumber = new StringBuilder(specimen.getType().getTypeHead()).append(specimen.getSerialNo()).append("A").append(String.format("%02d", i + 1)).toString();
				String filename = new StringBuilder(multipartConfigElement.getLocation()).append("/pdf/").append(printDate).append(serialNumber).append(".pdf").toString();

				if (print(template, serialNumber, specimen.getPatient().getName(), printDate, filename)) {
					count++;
				}
			}
			String serialNumber = new StringBuilder(specimen.getType().getTypeHead()).append(specimen.getSerialNo()).append(specimen.getLineType())
					.append(String.format("%02d", i + 1)).toString();
			String filename = new StringBuilder(multipartConfigElement.getLocation()).append("/pdf/").append(printDate).append(serialNumber).append(".pdf").toString();

			if (print(template, serialNumber, specimen.getPatient().getName(), printDate, filename)) {
				count++;
			}
		}
		records.setPrintCount(count);
		specimenPrintRecordsService.addSlidePrintRecords(records);

		return count;
	}

	public boolean print(SlideTemplate template, String serialNumber, String name, String date, String filename) {
		try {
			Map<String, SlideTemplateItem> itemMap = template.getItemMap();
			if (itemMap.containsKey("pn")) {
				itemMap.get("pn").setValue(name);
			}
			if (itemMap.containsKey("qr")) {
				itemMap.get("sn").setValue(serialNumber);
			}
			if (itemMap.containsKey("date")) {
				itemMap.get("date").setValue(date);
			}
			if (itemMap.containsKey("qr")) {
				itemMap.get("qr").setValue(serialNumber);
			}

			File pdfFile = new File(filename);
			if (pdfFile.exists()) {
				pdfFile.delete();
			}
			if (!pdfFile.getParentFile().exists()) {
				pdfFile.getParentFile().mkdirs();
			}

			// PdfUtils pdfUtils = new PdfUtils(filename);
			// pdfUtils.buildPdf(itemMap, template.getWidth());
			// Runtime.getRuntime().exec("cmd.exe /C start acrord32 /P /h " + pdfFile);
			// Runtime.getRuntime().exec("cmd.exe print " + pdfFile);

			
			
			
			return true;
		} catch (Exception e) {
			logger.error("Print pdf file failed :", filename);
		}

		return false;
	}

	public void printDocument(String filename, String printer) {
		try {
			PDDocument document = PDDocument.load(new FileInputStream(filename));
			PDFPrintable printable = new PDFPrintable(document, Scaling.SHRINK_TO_FIT);

			Paper paper = new Paper();
			//paper.setImageableArea(0, -3, 25 * PdfUtils.CR, 20 * PdfUtils.CR);
			PageFormat pageFormat = new PageFormat();
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			pageFormat.setPaper(paper);

			Book book = new Book();
			book.append(printable, pageFormat);

			PrinterJob printerJob = PrinterJob.getPrinterJob();
			printerJob.setPageable(book);
			printerJob.print();
		} catch (Exception e) {
			logger.error("Print pdf file failed :", filename);
		}
	}

}
