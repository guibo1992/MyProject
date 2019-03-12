package com.gb.chrom.quartz;

import java.io.InputStream;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.SpecimenAnalysisService;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenAnalysisResult;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.utils.AnalysisCaseUtils;

import jcifs.smb.NtlmPasswordAuthentication;

@Configuration
@EnableScheduling
public class AnalysisScheduler {

	private static Logger logger = LoggerFactory.getLogger(AnalysisScheduler.class);

	private static final String FILE_EXTENSION = ".xml";
	private static SpecimenQuery query = new SpecimenQuery();
	static {
		query.setCultureStatus(true);
		query.setAnalysisStatus(false);
		query.setLimit(Integer.MAX_VALUE);
	}

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private SpecimenAnalysisService specimenAnalysisService;
	@Autowired
	private MultipartConfigElement multipartConfigElement;
	
	//定时任务
	@Scheduled(cron = "0/30 * * * * ?")
	public void getSources() {
		logger.debug("Get specimen analysis results");

		List<Specimen> specimenList = specimenAnalysisService.querySpecimenAnalysisForPagingList(query).getList();
		if (CollectionUtils.isEmpty(specimenList)) {
			logger.debug("Not found specimens to be analyzed.");
			return;
		}

		com.gb.chrom.model.Configuration config = configurationService.getConfiguration();
		if (null == config || StringUtils.isBlank(config.getSmbDomain()) || StringUtils.isBlank(config.getNtlmUsername())) {
			logger.warn("System configuration is not valid.");
			return;
		}
		long sct = System.currentTimeMillis();
		try {
			//System.out.println("=========");
			//System.out.println(config.getSmbDomain());
			//System.out.println(config.getNtlmUsername());
			//System.out.println(config.getNtlmPassword());
			//System.out.println(AnalysisCaseUtils.getAuthentication(config.getSmbDomain(), config.getNtlmUsername(), config.getNtlmPassword()));
			NtlmPasswordAuthentication auth = AnalysisCaseUtils.getAuthentication(config.getSmbDomain(), config.getNtlmUsername(), config.getNtlmPassword());
			//System.out.println("+++++++");
			//System.out.println(auth);
			//System.out.println("+++++++");
			logger.debug("Connection used time: {}", System.currentTimeMillis() - sct);
			

			for (Specimen specimen : specimenList) {
				//System.out.println(auth);
				//System.out.println(config.getSmbDomain());
				//System.out.println( config.getShareSource());
				//System.out.println(specimen.getSpecimenNo());
				//System.out.println(specimen.getSpecimenNo()+ FILE_EXTENSION);	
				
				try (InputStream sourceInput = AnalysisCaseUtils.getInputStream(auth, config.getSmbDomain(), config.getShareSource(), specimen.getSpecimenNo(),
						specimen.getSpecimenNo() + FILE_EXTENSION)) {				
					if (null == sourceInput) {
						continue;
					}

					SpecimenAnalysisResult result = AnalysisCaseUtils.readAnalysisCaseResult(sourceInput);
					if (Validation.buildDefaultValidatorFactory().getValidator().validate(result, Default.class).size() > 0) {
						throw new RuntimeException("got analysis result has error.");
					}
					
					if (StringUtils.isNotBlank(result.getAnalysisKarImg())) {
						String karImg = AnalysisCaseUtils.copySmbFileToLocation(auth, config.getShareSource(), specimen.getSpecimenNo(),
								result.getAnalysisKarImg(), multipartConfigElement.getLocation());
						result.setAnalysisKarImg(karImg);

//						SmbFile file = AnalysisCaseUtils.getSmbFile(auth, config.getSmbDomain(), config.getShareSource(), specimen.getSpecimenNo(), result.getAnalysisKarImg());
//						result.setAnalysisKarImg(genLocationPath(file.getInputStream(), result.getAnalysisKarImg()));
//						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(multipartConfigElement.getLocation(), result.getAnalysisKarImg()));
					}
					if (StringUtils.isNotBlank(result.getAnalysisMetImg())) {
						String metImg = AnalysisCaseUtils.copySmbFileToLocation(auth, config.getShareSource(), specimen.getSpecimenNo(),
								result.getAnalysisMetImg(), multipartConfigElement.getLocation());
						result.setAnalysisMetImg(metImg);

						// SmbFile file = AnalysisCaseUtils.getSmbFile(auth, config.getSmbDomain(), config.getShareSource(), specimen.getSpecimenNo(),
						// result.getAnalysisMetImg());
						// result.setAnalysisMetImg(genLocationPath(file.getInputStream(), result.getAnalysisMetImg()));
						// FileUtils.copyInputStreamToFile(file.getInputStream(), new File(multipartConfigElement.getLocation(), result.getAnalysisMetImg()));
					}

					specimenAnalysisService.completeSpecimenAnalysis(result);
				} catch (Exception e) {				
					logger.error("Unable process analysis result or analysis result source can't touch, specimenNo: {}", specimen.getSpecimenNo());
				}
			}
		} catch (Exception e) {
			
			logger.error("Unable connect to {} analysis server, and used time: {}", config.getSmbDomain(), System.currentTimeMillis() - sct);
		}
	}

}