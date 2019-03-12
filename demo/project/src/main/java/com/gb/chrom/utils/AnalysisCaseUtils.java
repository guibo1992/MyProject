package com.gb.chrom.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gb.chrom.model.Configuration;
import com.gb.chrom.model.Patient;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenAnalysisResult;
import com.gb.chrom.model.SpecimenType;

import jcifs.Config;
import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbSession;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年7月24日
 * @since
 */
public class AnalysisCaseUtils {

	private static Logger logger = LoggerFactory.getLogger(AnalysisCaseUtils.class);

	// SMB 不能使用windows的文件分隔符, 必需使用 '/'
	public static final String FILE_SEPARATOR = "/";
	private static final String SBM_PROTOCOL = "smb://";

	static {
		// init jcifs config, 提升访问速度
		Config.setProperty("jcifs.smb.client.dfs.disabled", "true");
		Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");
		Config.setProperty("jjcifs.smb.lmCompatibility", "2");
		Config.setProperty("jcifs.smb.client.useExtendedSecurity", "false");
		Config.setProperty("jcifs.smb.client.soTimeout", "35000");
		Config.setProperty("jcifs.resolveOrder", "LMHOSTS,BCAST,DNS");
	}

	public static boolean testLogo(String domain, String username, String password, String source) {
		try {
			UniAddress address = UniAddress.getByName(domain);
			//System.out.println("----------");
			//System.out.println(domain);
			//System.out.println(username);
			//System.out.println(password);           
			NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, username, password);
			//NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication("smb://192.168.0.112","sinochrome08","rain100903");
			//System.out.println(address);
			//System.out.println("=====================");
			//System.out.println(authentication);
			//System.out.println("----------");
			SmbSession.logon(address, authentication);
			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during connect SMB server: ", e);
		}
		return false;
	}
	
	//解析服务器
	public static NtlmPasswordAuthentication getAuthentication(String domain, String username, String password) {
		try {
			UniAddress address = UniAddress.getByName(domain);
			
			NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, username, password);		
			SmbSession.logon(address, authentication);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException("Failed to connect analysis server.");
		}
	}

	public static InputStream getInputStream(NtlmPasswordAuthentication authentication, String domain, String source, String directory, String filename) throws Exception {
		try {
			String sourceUrl = new StringBuilder(SBM_PROTOCOL).append(domain).append(pathFormatter(source)).append(pathFormatter(directory)).toString();
			SmbFile sourceFile = new SmbFile(sourceUrl, filename, authentication);
			//System.out.println(sourceFile);
			if (!sourceFile.exists()) {
				logger.error("The file specified does not exist: {}", filename);
				return null;
			}
			
			return sourceFile.getInputStream();
		} catch (Exception e) {
			logger.error("Cannot get inputStream specified, file: {}", filename, e);
			throw e;
		}
	}
	
	public static String copySmbFileToLocation(NtlmPasswordAuthentication auth, String sorce, String parent, String filename, String location) throws Exception {
		StringBuilder builder = new StringBuilder(SBM_PROTOCOL);
		builder.append(auth.getDomain()).append(pathFormatter(sorce)).append(pathFormatter(parent)).append(filename);
		
		SmbFile sourceFile = new SmbFile(builder.toString(), auth);
		String path = genLocationPath(sourceFile.getInputStream(), FilenameUtils.getName(filename));
		FileUtils.copyInputStreamToFile(sourceFile.getInputStream(), new File(location, path));
		
		logger.debug("SMB File copy success.");
		
		return path;
	}
	
	public static SmbFile getSmbFile(NtlmPasswordAuthentication authentication, String domain, String source, String directory, String filename) throws Exception {
		source = new StringBuilder(SBM_PROTOCOL).append(domain).append(pathFormatter(source)).append(pathFormatter(directory)).append(filename).toString();
		try {
			SmbFile sourceFile = new SmbFile(source, authentication);
		
			if (!sourceFile.exists()) {
				logger.error("The file specified does not exist: {}", filename);
				return null;
			}

			return sourceFile;
		} catch (Exception e) {
			logger.error("Cannot get file specified: {}", source, e);
			throw e;
		}
	}
	
	//读取分析结果
	public static SpecimenAnalysisResult readAnalysisCaseResult(InputStream input) throws Exception {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(input);
		XPathFactory xFactory = XPathFactory.instance();

		// <Flag title="Completed" />
		XPathExpression<Element> flagExpr = xFactory.compile("/CWS/Case/Flag[@title='Completed']", Filters.element());
		Element flagElement = flagExpr.evaluateFirst(document);

		if (null == flagElement) {
			return null;
		}

		SpecimenAnalysisResult result = new SpecimenAnalysisResult();

		XPathExpression<Element> specimenExpr = xFactory.compile("/CWS/Case", Filters.element());
		Element specimenElement = specimenExpr.evaluateFirst(document);
		result.setSpecimenNo(specimenElement.getAttributeValue("name"));

		XPathExpression<Element> resultExpr = xFactory.compile("//CaseDetailsSet/CaseDetail[@title='Result']", Filters.element());
		Element resultElement = resultExpr.evaluateFirst(document);
		result.setAnalysisResult(resultElement.getAttributeValue("text"));

		XPathExpression<Element> DateExpr = xFactory.compile("//CaseDetailsSet/CaseDetail[@title='Date']", Filters.element());
		Element dateElement = DateExpr.evaluateFirst(document);
		String date = dateElement.getAttributeValue("text");
		if (StringUtils.isBlank(date)) {
			result.setCompletedTime(DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			result.setCompletedTime(date);
		}

		XPathExpression<Element> analystExpr = xFactory.compile("//CaseDetailsSet/CaseDetail[@title='Technologist']", Filters.element());
		Element analystElement = analystExpr.evaluateFirst(document);
		result.setLaboratorian(analystElement.getAttributeValue("text"));

		XPathExpression<Element> remarkExpr = xFactory.compile("//CaseDetailsSet/CaseDetail[@title='Case Comment']", Filters.element());
		Element remarkElement = remarkExpr.evaluateFirst(document);
		result.setComment(remarkElement.getAttributeValue("text"));

		// .kar 排序的结果图, .met 结果图
		XPathExpression<Element> imageExpr = xFactory.compile("//Blob[@blobtype='.met']", Filters.element());
		Element imgElement = imageExpr.evaluateFirst(document);
		if (null != imgElement) {
			result.setAnalysisMetImg(imgElement.getAttributeValue("blobpath"));
		}

		imageExpr = xFactory.compile("//Blob[@blobtype='.kar']", Filters.element());
		imgElement = imageExpr.evaluateFirst(document);
		if (null != imgElement) {
			result.setAnalysisKarImg(imgElement.getAttributeValue("blobpath"));
		}

		return result;
	}
	
	//创建输出流
	public static OutputStream getOutputStream(Configuration ntlmConfig, String outputFile) {
		try {
			UniAddress address = UniAddress.getByName(ntlmConfig.getSmbDomain());
			NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(ntlmConfig.getSmbDomain(), ntlmConfig.getNtlmUsername(),
					ntlmConfig.getNtlmPassword());
			SmbSession.logon(address, authentication);

			String parentSource = getParentPath(ntlmConfig.getShareSource());
			outputFile = new StringBuilder(SBM_PROTOCOL).append(ntlmConfig.getSmbDomain()).append(parentSource).append(outputFile).toString();
			SmbFile output = new SmbFile(outputFile, authentication);

			return output.getOutputStream();
		} catch (Exception e) {
			logger.error("Exception occurred during output source file: ", e);
			throw new RuntimeException("File stream output failed.", e);
		}
	}
	
	//输出xml文件  案例模板
	public static void outputCaseTemplate(Specimen specimen, OutputStream output) {
		try {
			Document document = new Document();
			Element rootElement = new Element("CWS");
			Element caseElement = new Element("Case");
			Element caseDetailSetElement = new Element("CaseDetailsSet");

			document.setRootElement(rootElement);
			rootElement.addContent(caseElement);
			caseElement.setAttribute("name", specimen.getSpecimenNo());
			caseElement.addContent(caseDetailSetElement);

			SpecimenType type = specimen.getType();
			Patient patient = specimen.getPatient();

			if (null == type || null == patient) {
				logger.error("Invalid specimen type or patient information.");
				throw new RuntimeException("Invalid case template params.");
			}

			Element patientNameNode = new Element("CaseDetail");
			patientNameNode.setAttribute("title", "Patient name");
			patientNameNode.setAttribute("text", getValue(patient.getName()));
			patientNameNode.setAttribute("type", "system");
			patientNameNode.setAttribute("ctrltype", "Text").setAttribute("editable", "False").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(patientNameNode);

			Element birthdayNode = new Element("CaseDetail");
			birthdayNode.setAttribute("title", "Date of birth");
			birthdayNode.setAttribute("text", getValue(patient.getBirthdate()));
			birthdayNode.setAttribute("type", "system");
			birthdayNode.setAttribute("ctrltype", "Date").setAttribute("editable", "False").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(birthdayNode);

			Element typeNode = new Element("CaseDetail");
			typeNode.setAttribute("title", "Specimen Type");
			typeNode.setAttribute("text", getValue(type.getName()));
			typeNode.setAttribute("type", "system");
			typeNode.setAttribute("ctrltype", "Text").setAttribute("editable", "False").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(typeNode);

			Element clinicalNode = new Element("CaseDetail");
			clinicalNode.setAttribute("title", "Referral reason");
			clinicalNode.setAttribute("text", getValue(specimen.getClinicalInfo()));
			clinicalNode.setAttribute("type", "system");
			clinicalNode.setAttribute("ctrltype", "MultiLineText").setAttribute("editable", "False").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(clinicalNode);

			Element technologistNode = new Element("CaseDetail");
			technologistNode.setAttribute("title", "Technologist");
			technologistNode.setAttribute("text", getValue(specimen.getLaboratorian().getName()));
			technologistNode.setAttribute("type", "system");
			technologistNode.setAttribute("ctrltype", "Text").setAttribute("editable", "True").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(technologistNode);

			Element timeNode = new Element("CaseDetail");
			timeNode.setAttribute("title", "Date");
			timeNode.setAttribute("text", getValue(specimen.getGmtCreate()));
			timeNode.setAttribute("type", "system");
			timeNode.setAttribute("ctrltype", "Date").setAttribute("editable", "True").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(timeNode);

			Element commentElement = new Element("CaseDetail");
			commentElement.setAttribute("title", "Case Comment");
			commentElement.setAttribute("text", getValue(specimen.getRemarks()));
			commentElement.setAttribute("type", "system");
			commentElement.setAttribute("ctrltype", "MultiLineText").setAttribute("editable", "True").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(commentElement);

			Element resultNode = new Element("CaseDetail");
			resultNode.setAttribute("title", "Result");
			resultNode.setAttribute("text", "result text");
			resultNode.setAttribute("type", "system");
			resultNode.setAttribute("ctrltype", "Text").setAttribute("editable", "True").setAttribute("mandatory", "False");
			caseDetailSetElement.addContent(resultNode);

			Element slideElement = null;
			for (int i = 0; i < specimen.getLinePrintCount(); i++) { // 一个标本下多个玻片 (多张打印)
				slideElement = new Element("ScanSlide");
				slideElement.setAttribute("barcode", specimen.getSpecimenNo() + "A" + (i + 1)).setAttribute("templatename", type.getCaseTemplate());
				// slideElement.setAttribute("barcode", sepcimen.getSpecimenNo() + "a" + String.valueOf((char) (i + 97)))

				// slideElement.addContent(new Element("ScanDetailsSet"));
				caseElement.addContent(slideElement);
			}

			if (StringUtils.equalsIgnoreCase("B", specimen.getLineType())) {
				for (int i = 0; i < specimen.getLinePrintCount(); i++) {
					slideElement = new Element("ScanSlide");
					slideElement.setAttribute("barcode", specimen.getSpecimenNo() + "B" + (i + 1)).setAttribute("templatename", type.getCaseTemplate());
					// slideElement.setAttribute("barcode", sepcimen.getSpecimenNo() + "b" + String.valueOf((char) (i + 97)))
					// slideElement.addContent(new Element("ScanDetailsSet"));
					caseElement.addContent(slideElement);
				}
			}

			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			outputter.output(document, output);
		} catch (IOException e) {
			logger.error("Exception occurred during output case template: ", e);
			throw new RuntimeException("Case template output failed.", e);
		}
	}

	static String getValue(Object value) {
		if (null == value) {
			return "";
		}
		if (value instanceof String) {
			return (String) value;
		}
		if (value instanceof Date) {
			return DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
		}
		return value.toString();
	}

	static String pathFormatter(String source) {
		if (StringUtils.isBlank(source)) {
			return FILE_SEPARATOR;
		}

		if (!source.endsWith(FILE_SEPARATOR)) {
			source += FILE_SEPARATOR;
		}

		return source;
	}

	static String getParentPath(String path) {
		if (StringUtils.isBlank(path)) {
			return FILE_SEPARATOR;
		}

		int index = path.lastIndexOf("/");
		if (index == -1) {
			return FILE_SEPARATOR + path + FILE_SEPARATOR;
		}
		return path.substring(0, index + 1);
	}
	
	private static String genLocationPath(InputStream input, String filename) throws IOException {
		String md5Str = DigestUtils.md5Hex(input);
		String ext = FilenameUtils.getExtension(filename);

		StringBuffer buffer = new StringBuffer("/images/");
		buffer.append(DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyy"));
		buffer.append(AnalysisCaseUtils.FILE_SEPARATOR).append(md5Str.substring(0, 8));
		buffer.append(AnalysisCaseUtils.FILE_SEPARATOR).append(md5Str.substring(8, 16));
		buffer.append(AnalysisCaseUtils.FILE_SEPARATOR).append(md5Str.substring(16)).append(".").append(ext);

		return buffer.toString();
	}

	public static void main(String[] args) throws Exception {
//		NtlmPasswordAuthentication auth = AnalysisCaseUtils.getAuthentication("192.168.43.116", "Slidepath", "Daedalus08", "/CVTransferSpool");
//		try (InputStream resultInput = AnalysisCaseUtils.getInputStream(auth, "192.168.43.116", "/CVTransferSpool", "/CompletedCases/B20180700014",
//				"B20180700014.xml")) {
//
//			SpecimenAnalysisResult result = AnalysisCaseUtils.readAnalysisCaseResult(resultInput);
//
//			System.out.println(result.getAnalysisResult());
//			System.out.println(result.getAnalysisKarImg());
//			System.out.println(result.getAnalysisMetImg());
//
//			try (InputStream metInput = AnalysisCaseUtils.getInputStream(auth, "192.168.43.116", "/CVTransferSpool", "/CompletedCases/B20180700014",
//					result.getAnalysisMetImg())) {
//				FileUtils.copyInputStreamToFile(metInput, new File("d:/caseResult/" + result.getAnalysisMetImg()));
//			}
//
//			try (InputStream karInput = AnalysisCaseUtils.getInputStream(auth, "192.168.43.116", "/CVTransferSpool", "/CompletedCases/B20180700014",
//					result.getAnalysisKarImg())) {
//				FileUtils.copyInputStreamToFile(karInput, new File("d:/caseResult/" + result.getAnalysisKarImg()));
//			}
//		}

	}

}
