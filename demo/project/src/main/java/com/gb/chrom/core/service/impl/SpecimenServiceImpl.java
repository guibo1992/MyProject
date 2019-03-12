package com.gb.chrom.core.service.impl;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.PatientMapper;
import com.gb.chrom.core.mapper.SpecimenClinicalInfoMapper;
import com.gb.chrom.core.mapper.SpecimenMapper;
import com.gb.chrom.core.mapper.SpecimenTypeMapper;
import com.gb.chrom.core.service.ConfigurationService;
import com.gb.chrom.core.service.SerialNumberRuleService;
import com.gb.chrom.core.service.SpecimenService;
import com.gb.chrom.model.Configuration;
import com.gb.chrom.model.Patient;
import com.gb.chrom.model.SerialNumberRule;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.SpecimenClinicalInfo;
import com.gb.chrom.model.SpecimenType;
import com.gb.chrom.model.User;
import com.gb.chrom.model.query.SpecimenQuery;
import com.gb.chrom.utils.AnalysisCaseUtils;
import com.gb.chrom.utils.DocumenPrinter;
import com.gb.chrom.utils.PdfDocumentBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @since 1.0
 * @author Summer
 */
@Service
public class SpecimenServiceImpl implements SpecimenService {

	private static final Logger logger = LoggerFactory.getLogger(SpecimenService.class);

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private SpecimenMapper specimenMapper;
	@Autowired
	private SpecimenTypeMapper specimenTypeMapper;
	@Autowired
	private SpecimenClinicalInfoMapper specimenClinicalInfoMapper;

	@Autowired
	private SerialNumberRuleService serialNumberRuleService;
	@Autowired
	private ConfigurationService configurationService;

	@Value("${spring.profiles.active}")
	private String profilesActive;

	@Transactional
	@Override
	public boolean addSpecimen(Specimen specimen, List<User> analystList) {
		try {
			Configuration config = configurationService.getConfiguration();
			SerialNumberRule rule = serialNumberRuleService.getSerialNumberRule();
			String year = DateFormatUtils.format(Calendar.getInstance(), rule.getTimeFormat());

			synchronized (specimenMapper) {
				int intYear = Integer.parseInt(year);
				Long maxSN = specimenMapper.findSpecimenYearMaxSerialNo(specimen.getTypeId(), Calendar.getInstance().get(Calendar.YEAR));

				if (null == maxSN) {
					maxSN = (long) (intYear * Math.pow(10, rule.getSnLength()));
				}
				specimen.setSerialNo(++maxSN);
				specimen.setLineType(rule.getLineType());
				specimen.setLinePrintCount(rule.getLinePrintCount());

				StringBuilder builder = new StringBuilder();
				builder.append(specimen.getSerialNo());
				SpecimenType type = specimenTypeMapper.findSpecimenTypeById(specimen.getTypeId());

				if (rule.getIsTypeHead()) {
					builder.insert(0, type.getTypeHead());
				}
				specimen.setSpecimenNo(builder.toString());

				Patient patient = specimen.getPatient();
				patient.setHisId(specimen.getHisId());
				patientMapper.savePatient(patient);
				specimen.setPatientId(patient.getId());

				// distribute specimen
				int size = analystList.size();
				Long lastId = specimenMapper.findLastSpecimenAnalystId(specimen.getTypeId(), User.TYPE_OF_ANALYST);

				if (null == lastId) {
					specimen.setLaboratorian(analystList.get(0));
				} else {
					for (int i = 0; i < size; i++) {
						if (lastId.longValue() == analystList.get(i).getId()) {
							specimen.setLaboratorian(analystList.get(++i % size));
						}
					}
				}
				specimen.setLaboratorianId(specimen.getLaboratorian().getId());

				Subject subject = SecurityUtils.getSubject();
				User currUser = (User) subject.getSession().getAttribute(User._ACTIVE_USER_JSESSIONID);

				specimen.setCreaterId(currUser.getId());
				// specimen.setSpecimenStatus(SpecimenStatus.STATUS_OF_DEFAULT);
				specimen.setGmtCreate(Calendar.getInstance().getTime());
				specimenMapper.saveSpecimen(specimen);

				List<SpecimenClinicalInfo> clinicalInfoList = specimen.getClinicalInfoList();

				if (CollectionUtils.isNotEmpty(clinicalInfoList)) {
					Iterator<SpecimenClinicalInfo> iterator = clinicalInfoList.iterator();

					while (iterator.hasNext()) {
						SpecimenClinicalInfo sci = iterator.next();

						if (StringUtils.isBlank(sci.getClinicalInfo()) && StringUtils.isBlank(sci.getClinicalRemarks())) {
							iterator.remove();
							continue;
						}
						sci.setSpecimenId(specimen.getId());
						sci.setPatientId(patient.getId());
					}

					if (CollectionUtils.isNotEmpty(clinicalInfoList)) {
						specimenClinicalInfoMapper.saveClinicalInfoList(clinicalInfoList);
					}
				}

				if (StringUtils.isNotBlank(config.getSmbDomain())) {
					String caseTmplFile = specimen.getSpecimenNo() + ".xml";

					try (OutputStream output = AnalysisCaseUtils.getOutputStream(config, caseTmplFile)) {
						specimen.setType(type);
						AnalysisCaseUtils.outputCaseTemplate(specimen, output);
					}
				}
			}

			if (null != config && StringUtils.isNotBlank(config.getBarcodePrinter())) {
				Patient patinet = specimen.getPatient();
				String name = null == patinet ? "" : (StringUtils.isNotBlank(patinet.getName()) ? patinet.getName() : "");

				DocumenPrinter.prindSpecimenBarcodeDocument(PdfDocumentBuilder.BarcodePdfDocumentBuilder.builder(name, specimen.getSpecimenNo()),
						config.getBarcodePrinter());
				if (StringUtils.containsIgnoreCase(specimen.getLineType(), "B")) {
					DocumenPrinter.prindSpecimenBarcodeDocument(PdfDocumentBuilder.BarcodePdfDocumentBuilder.builder(name, specimen.getSpecimenNo()),
							config.getBarcodePrinter());
				}
			}

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during save specimen :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Transactional
	@Override
	public boolean updateSpecimen(Specimen specimen) {
		try {
			specimenClinicalInfoMapper.deleteClinicalInfoBySpecimenId(specimen.getId());

			if (specimen.getType() != null) {
				SerialNumberRule rule = serialNumberRuleService.getSerialNumberRule();

				SpecimenType type = specimenTypeMapper.findSpecimenTypeById(specimen.getTypeId());
				specimen.setLineType(rule.getLineType());
				specimen.setLinePrintCount(rule.getLinePrintCount());
				specimen.setSpecimenNo(type.getTypeHead() + specimen.getSerialNo());
			}
			int affect = specimenMapper.updateSpecimen(specimen);
			Specimen specimen1 = specimenMapper.findSpecimenById(specimen.getId());

			Patient patient = specimen.getPatient();
			patient.setId(specimen1.getPatientId());
			patientMapper.updatePatient(specimen.getPatient());

			if (CollectionUtils.isNotEmpty(specimen.getClinicalInfoList())) {
				specimen.getClinicalInfoList().forEach(ci -> {
					ci.setSpecimenId(specimen.getId());
					ci.setPatientId(patient.getId());
				});
				specimenClinicalInfoMapper.saveClinicalInfoList(specimen.getClinicalInfoList());
			}

			return affect > 0;
		} catch (Exception e) {
			logger.error("Exception occurred during update specimen :", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean deleteSpecimen(long id) {
		try {
			Specimen specimen = new Specimen();
			specimen.setId(id);
			specimen.setStatus(false);

			return specimenMapper.updateSpecimen(specimen) > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during delete specimen :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean undeleteSpecimen(long id) {
		try {
			Specimen specimen = new Specimen();
			specimen.setId(id);
			specimen.setStatus(true);

			return specimenMapper.updateSpecimen(specimen) > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during delete specimen :", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Specimen querySpecimen(long id) {
		try {
			Specimen specimen = specimenMapper.findSpecimenById(id);
			if (null != specimen) {
				specimen.setClinicalInfoList(specimenClinicalInfoMapper.findClinicalInfoForList(id));
			}

			return specimen;
		} catch (MapperException e) {
			logger.error("Exception occurred during query specimen :", e);
		}
		return null;
	}

	@Override
	public Specimen querySpecimen(String hisId) {
		try {
			return specimenMapper.findSpecimenByHisId(hisId);
		} catch (MapperException e) {
			logger.error("Exception occurred during query specimen by hisId :", e);
		}
		return null;
	}

	@Override
	public boolean validateSpecimenNo(String specimenNo) {
		try {
			return Optional.ofNullable(specimenMapper.findSpecimenBySpecimenNo(specimenNo)).isPresent();
		} catch (MapperException e) {
			logger.error("Exception occurred during validate specimenNo :", e);
		}
		return false;
	}

	@Override
	public PageInfo<Specimen> querySpecimenForPagingList(SpecimenQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return specimenMapper.findSpecimenForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query specimen for paging list :", e);
		}
		return new PageInfo<>();
	}

}
