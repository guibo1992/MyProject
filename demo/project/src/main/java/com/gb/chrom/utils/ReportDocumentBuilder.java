package com.gb.chrom.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gb.chrom.model.AnalysisReport;
import com.gb.chrom.model.ReportTemplate;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
//生成pdf报告
/**
 * @since 1.0
 * @author Summer
 */
public class ReportDocumentBuilder {

	private static Logger logger = LoggerFactory.getLogger(ReportDocumentBuilder.class);

	static final int PRINT_DPI = 300;

	Document document;
	PdfWriter writer;
	BaseFont baseFont;

	static FontFactoryImp fontFactory;

	static {
		fontFactory = new FontFactoryImp();

		if (StringUtils.containsIgnoreCase(System.getProperties().getProperty("os.name"), "windows")) {
			fontFactory.register(System.getenv().get("SystemRoot") + "/Fonts/simsun.ttc");
		} else {
			fontFactory.registerDirectory("/usr/local/Fonts");
		}
	}

	public ReportDocumentBuilder(String output) throws Exception {
		Rectangle rectangle = PageSize.A4;
		document = new Document(rectangle);
		writer = PdfWriter.getInstance(document, new FileOutputStream(output));
	}

	private Font getFont(float size, int style) throws DocumentException, IOException {
		if (fontFactory.getRegisteredFonts().contains("宋体")) {
			return fontFactory.getFont("宋体", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, size, style);
		} else {
			BaseFont basefont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			return new Font(basefont, size, style);
		}
	}

	public void build(AnalysisReport report, String hospname, String hosplogo, String location) throws Exception {
		try {
			document.open();
			document.newPage();

			Phrase phrase = null;
			Paragraph paragraph = null;

			Font titleFont = getFont(22, Font.BOLD); // 一级标题
			Font subheadFont = getFont(14, Font.BOLD); // 副标题
			Font textFont = getFont(10, Font.NORMAL);
			Font boldFont = getFont(10, Font.BOLD);

			if (report.getTemplate().getLogo()) {
				// logo
				addLogo(location, hosplogo, 65, 65, 20);
			}
			
			// Title
			paragraph = new Paragraph(hospname, titleFont);
			paragraph.setLeading(35);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			paragraph = new Paragraph(report.getName(), subheadFont);
			paragraph.setLeading(25);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			paragraph = new Paragraph();
			paragraph.setLeading(26);
			LineSeparator line = new LineSeparator();
			line.setLineWidth(3);
			line.setLineColor(BaseColor.GRAY);
			paragraph.add(new Chunk(line));
			document.add(paragraph);

			// specimen info
			addSpecimenInfo(report);

			paragraph = new Paragraph();
			paragraph.setLeading(10);
			line = new LineSeparator();
			line.setLineWidth(1);
			line.setLineColor(BaseColor.GRAY);
			paragraph.add(new Chunk(line));
			document.add(paragraph);
			document.add(new Paragraph(8, " "));

			paragraph = new Paragraph();
			paragraph.setLeading(14);
			paragraph.setIndentationLeft(2 * boldFont.getSize());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("检测结果： ", boldFont));
			phrase.add(new Chunk(report.getAnalysisResult(), textFont));
			paragraph.add(phrase);
			document.add(paragraph);

			// result images
			addResultImages(report.getTemplate(), location, report.getAnalysisKarImg(), report.getAnalysisMetImg());

			if (report.getTemplate().getResultInterpretation()) {
				paragraph = new Paragraph();
				paragraph.setLeading(16);
				paragraph.setIndentationLeft(2 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				phrase = new Phrase();
				phrase.add(new Chunk("结果解释：  ", boldFont));
				paragraph.add(phrase);
				document.add(paragraph);
	
				paragraph = new Paragraph();
				paragraph.setLeading(14);
				paragraph.setIndentationLeft(4 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				phrase = new Phrase();
				phrase.add(new Chunk(null == report.getResultInterpretation() ? "" : report.getResultInterpretation(), textFont));
				paragraph.add(phrase);
				document.add(paragraph);
				document.add(new Paragraph(8, " "));
	
				paragraph = new Paragraph();
				paragraph.setLeading(16);
				line = new LineSeparator();
				line.setLineWidth(1);
				line.setLineColor(BaseColor.GRAY);
				paragraph.add(new Chunk(line));
				document.add(paragraph);
				document.add(new Paragraph(8, " "));
			}
			
			if (report.getTemplate().getReportConclusion()) {
				paragraph = new Paragraph();
				paragraph.setLeading(16);
				paragraph.setIndentationLeft(2 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				phrase = new Phrase();
				phrase.add(new Chunk("结论：  ", boldFont));
				paragraph.add(phrase);
				document.add(paragraph);
	
				paragraph = new Paragraph();
				paragraph.setLeading(14);
				paragraph.setIndentationLeft(4 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				paragraph.setPaddingTop(10);
				phrase = new Phrase();
				phrase.add(new Chunk(null == report.getReportConclusion() ? "" : report.getReportConclusion(), textFont));
				paragraph.add(phrase);
				document.add(paragraph);
				document.add(new Paragraph(8, " "));
			}
			System.out.println(report.getTemplate().getHideRemarks());
			System.out.println(report.getRemarks());
			if (!report.getTemplate().getHideRemarks()) {
				paragraph = new Paragraph();
				paragraph.setLeading(16);
				paragraph.setIndentationLeft(2 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				phrase = new Phrase();
				phrase.add(new Chunk("备注：  ", boldFont));
				paragraph.add(phrase);
				document.add(paragraph);

				paragraph = new Paragraph();
				paragraph.setLeading(14);
				paragraph.setIndentationLeft(4 * boldFont.getSize());
				paragraph.setAlignment(Element.ALIGN_LEFT);
				phrase = new Phrase();
				phrase.add(new Chunk(report.getRemarks(), textFont));
				paragraph.add(phrase);
				document.add(paragraph);
				document.add(new Paragraph(8, " "));
			}

			// PDF footer
			PdfContentByte content = writer.getDirectContent();
			content.beginText();
			content.setTextRise(0f);
			content.setColorFill(BaseColor.BLACK);
			content.setFontAndSize(textFont.getBaseFont(), 10);

			content.showTextAligned(PdfContentByte.ALIGN_LEFT, "检验人：", 60, 50, 0);
			content.showTextAligned(PdfContentByte.ALIGN_LEFT, null == report.getLaboratorian() ? "" : report.getLaboratorian(), 100, 50, 0);

			content.showTextAligned(PdfContentByte.ALIGN_LEFT, "审核人：", 220, 50, 0);
			content.showTextAligned(PdfContentByte.ALIGN_LEFT, report.getMasterAuditor(), 260, 50, 0);
			content.showTextAligned(PdfContentByte.ALIGN_LEFT, null == report.getDeputyAuditor() ? "" : report.getDeputyAuditor(), 300, 50, 0);

			content.showTextAligned(PdfContentByte.ALIGN_LEFT, "日期：", 400, 50, 0);
			content.showTextAligned(PdfContentByte.ALIGN_LEFT, report.getReportDate(), 430, 50, 0);
			content.endText();

			document.close();
		} catch (Exception e) {
			logger.error("build analysis report failed: ", e);
			throw e;
		}
	}

	/**
	 * insert image
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void addLogo(String location, String filename, float width, float height, float absoluteX) throws Exception {
		if (StringUtils.isBlank(filename)) {
			return;
		}

		Image logoImg = Image.getInstance(location + filename);
		logoImg.scaleToFit(new Rectangle(0, 0, width, height));
		logoImg.setAlignment(Element.ALIGN_RIGHT);
		Chunk chunk = new Chunk(logoImg, 0, -72);
		chunk.setLineHeight(height);
		document.add(chunk);
	}

	/**
	 * insert image
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void addResultImages(ReportTemplate template, String location, String... images) throws Exception {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Element.ALIGN_CENTER);

		Chunk chunk = null;

		for (int i = 0; i < images.length; i++) {
			if (StringUtils.isBlank(images[i])) {
				continue;
			}

			Image image = Image.getInstance(location + images[i]);
			image.setDpi(PRINT_DPI, PRINT_DPI);
			image.scaleToFit(new Rectangle(0, 0, 250, 250));
			image.setAlignment(Element.ALIGN_CENTER);
			chunk = new Chunk(image, 0, 0);
			chunk.setLineHeight(250);

			paragraph.setLeading(250 * image.getHeight() / image.getWidth() + 20);
			paragraph.add(chunk);
		}
		document.add(paragraph);
		document.add(new Paragraph(8, " "));
	}

	public void addSpecimenInfo(AnalysisReport report) throws Exception {
		Font textFont = getFont(10, Font.NORMAL);
		Font boldFont = getFont(10, Font.BOLD);

		PdfPTable table = new PdfPTable(3);
		table.setSpacingBefore(10.0f);
		table.setWidthPercentage(92);
		Paragraph paragraph = new Paragraph();
		Phrase phrase = new Phrase();
		PdfPCell cell;

		if (report.getTemplate().getPatientName()) {		
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setMinimumHeight(25);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("姓 名： ", boldFont));
			phrase.add(new Chunk(null == report.getPatientName() ? "" : report.getPatientName(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getHideResultSex()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("性 别：  ", boldFont));
			phrase.add(new Chunk(null == report.getPatientSex() ? "" : report.getPatientSex(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getPatientAge()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("年 龄： ", boldFont));
			phrase.add(new Chunk(null == report.getPatientAge() ? "" : report.getPatientAge() + "岁", textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getHisId()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setMinimumHeight(25);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("门诊号：  ", boldFont));
			phrase.add(new Chunk(null == report.getHisId() ? "" : report.getHisId(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getInspectionDept()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("送检科室： ", boldFont));
			phrase.add(new Chunk(null == report.getInspectionDept() ? "" : report.getInspectionDept(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getInspectionPhysician()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("送检医生：", boldFont));
			phrase.add(new Chunk(null == report.getInspectionPhysician() ? "" : report.getInspectionPhysician(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getInspectionType()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setMinimumHeight(25);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("送检样本：", boldFont));
			phrase.add(new Chunk(null == report.getInspectionType() ? "" : report.getInspectionType(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		if (report.getTemplate().getSpecimenDate()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("采样日期： ", boldFont));
			phrase.add(new Chunk(null == report.getSpecimenDate() ? "" : report.getSpecimenDate(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
		}
		
		if (report.getTemplate().getSpecimenNo()) {
			cell = new PdfPCell();
			cell.setBorderWidth(0);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("样本号： ", boldFont));
			phrase.add(new Chunk(report.getSpecimenNo(), textFont));
			cell.addElement(phrase);
			table.addCell(cell);
			document.add(table);
		}
		
		if (report.getTemplate().getClinicalInfo()) {
			paragraph = new Paragraph();
			paragraph.setLeading(16);
			paragraph.setIndentationLeft(2 * boldFont.getSize());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("送检指征：  ", boldFont));
			paragraph.add(phrase);
			document.add(paragraph);
		
			paragraph = new Paragraph();
			paragraph.setLeading(14);
			paragraph.setIndentationLeft(4 * boldFont.getSize());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk(null == report.getClinicalInfo() ? "" : report.getClinicalInfo(), textFont));
			paragraph.add(phrase);
			document.add(paragraph);
			document.add(new Paragraph(8, " "));
		}

		if (StringUtils.isNotBlank(report.getDetectionMethod())) {
			paragraph = new Paragraph();
			paragraph.setLeading(16);
			paragraph.setIndentationLeft(2 * boldFont.getSize());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk("检测方法：  ", boldFont));
			paragraph.add(phrase);
			document.add(paragraph);

			paragraph = new Paragraph();
			paragraph.setLeading(14);
			paragraph.setIndentationLeft(4 * boldFont.getSize());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			phrase = new Phrase();
			phrase.add(new Chunk(report.getDetectionMethod(), textFont));
			paragraph.add(phrase);
			document.add(paragraph);
			document.add(new Paragraph(8, " "));
		}
	}

}
