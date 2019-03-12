package com.gb.chrom.utils;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.Orientation;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

/**
 * @author Summer
 *
 *         2018年8月20日
 */
public class DocumenPrinter {

	public static void printSpecimenDocumentBytes(byte[] docBytes, String printer) throws Exception {
		try (PDDocument document = PDDocument.load(docBytes)) {
			PrinterJob job = PrinterJob.getPrinterJob();

			for (PrintService service : PrinterJob.lookupPrintServices()) {
				if (StringUtils.equalsIgnoreCase(service.getName(), printer)) {
					job.setPrintService(service);
					break;
				}
			}

			Paper paper = new Paper();
			paper.setSize(21 * CR, 16.5 * CR);
			paper.setImageableArea(1.25, 0, paper.getWidth(), paper.getHeight());

			PageFormat pageFormat = new PageFormat();
			pageFormat.setPaper(paper);
			pageFormat.setOrientation(PageFormat.PORTRAIT);

			Book book = new Book();
			book.append(new PDFPrintable(document, Scaling.SCALE_TO_FIT), pageFormat, 1);
			job.setPageable(book);

			job.print();
		}
	}

	public static void prindSpecimenBarcodeDocument(byte[] docBytes, String printer) throws Exception {
		try (PDDocument document = PDDocument.load(docBytes)) {
			PrinterJob job = PrinterJob.getPrinterJob();

			for (PrintService service : PrinterJob.lookupPrintServices()) {
				System.out.println(service.getName());
				if (StringUtils.equalsIgnoreCase(service.getName(), printer)) {
					job.setPrintService(service);
					break;
				}
			}

			job.setPageable(new PDFPageable(document, Orientation.PORTRAIT, false, 300));

			Paper paper = new Paper();
			paper.setSize(50 * CR, 25 * CR);
			paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());

			PageFormat pageFormat = new PageFormat();
			pageFormat.setPaper(paper);
			pageFormat.setOrientation(PageFormat.PORTRAIT);

			Book book = new Book();
			book.append(new PDFPrintable(document, Scaling.SCALE_TO_FIT), pageFormat, 1);
			job.setPageable(book);

			job.print();
		}
	}

	/**
	 * 
	 * @param filename - file of print
	 * @param printer  - printer name
	 * @throws Exception
	 */
	public static void prindReportDocument(String filename, String printer) throws Exception {
		FileInputStream input = new FileInputStream(filename);

		try (PDDocument document = PDDocument.load(input)) {
			PrinterJob job = PrinterJob.getPrinterJob();

			for (PrintService service : PrinterJob.lookupPrintServices()) {
				if (StringUtils.equalsIgnoreCase(service.getName(), printer)) {
					job.setPrintService(service);
					break;
				}
			}

			job.setPageable(new PDFPageable(document, Orientation.PORTRAIT, false, 300));

			Paper paper = new Paper();
			paper.setSize(598, 842); // 1/72 inch
			paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());

			PageFormat pageFormat = new PageFormat();
			pageFormat.setPaper(paper);
			pageFormat.setOrientation(PageFormat.PORTRAIT);

			Book book = new PDFPageable(document, Orientation.PORTRAIT, false, 300);
			// append all pages 设置一些属性 是否缩放 打印张数等
			book.append(new PDFPrintable(document, Scaling.SCALE_TO_FIT), pageFormat, 1);
			job.setPageable(book);

			// PrintRequestAttributeSet attrSet = new HashPrintRequestAttributeSet();
			// attrSet.add(new Copies(count));
			// attrSet.add(MediaSize.findMedia(29, 24, Size2DSyntax.MM));
			// attrSet.add(new MediaPrintableArea(0, 0, 35 * CR, 30 * CR, Size2DSyntax.INCH));
			// job.print(attrSet);

			job.setPageable(book);

//			job.print(attrSet);
		}
	}

	public static List<String> getPrinterList() {
		List<String> printerList = new ArrayList<>();
		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);

		for (int i = 0; i < printService.length; i++) {
			printerList.add(printService[i].getName());
		}

		return printerList;
	}

	public static final float CR = 72f / 25.4f;

}