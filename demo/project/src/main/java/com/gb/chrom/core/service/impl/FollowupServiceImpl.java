package com.gb.chrom.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.gb.chrom.core.MapperException;
import com.gb.chrom.core.mapper.FollowupMapper;
import com.gb.chrom.core.mapper.FollowupRecordMapper;
import com.gb.chrom.core.mapper.PatientMapper;
import com.gb.chrom.core.mapper.SpecimenMapper;
import com.gb.chrom.core.service.FollowupService;
import com.gb.chrom.model.Followup;
import com.gb.chrom.model.FollowupRecord;
import com.gb.chrom.model.Patient;
import com.gb.chrom.model.Specimen;
import com.gb.chrom.model.query.FollowupQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月25日
 * @since
 */
@Service
public class FollowupServiceImpl implements FollowupService {

	private static Logger logger = LoggerFactory.getLogger(FollowupService.class);

	@Autowired
	private FollowupMapper followupMapper;

	@Autowired
	private FollowupRecordMapper followupRecordMapper;

	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private SpecimenMapper specimenMapper;

	@Override
	@Transactional
	public boolean addFollowupRecord(FollowupRecord record) {
		try {
			Specimen specimen = specimenMapper.findSpecimenBySpecimenNo(record.getSpecimenNo());
			record.setHisId(specimen.getHisId());
			followupRecordMapper.saveFollowupRecord(record);

			synchronized (followupMapper) {
				Followup follow = followupMapper.findFollowupBySpecimenNo(record.getSpecimenNo());

				if (null == follow) {
					follow = new Followup();
					Patient patient = patientMapper.findPatientByHisId(record.getHisId());
					if (null != patient) {
						follow.setPatientName(patient.getName());
						follow.setPatientSex(patient.getSex());
						follow.setPatientBirthdate(patient.getBirthdate());
					}

					follow.setHisId(record.getHisId());
					follow.setSpecimenNo(record.getSpecimenNo());
					follow.setFirstFollowupDate(record.getFollowupDate());
					follow.setLastFollowupDate(record.getFollowupDate());
					follow.setFollowupTotalCount(1);
					follow.setLostFollowupCount(0);
					follow.setSuccessFollowupCount(0);
					follow.setRejectFollowupCount(0);

					if (record.getFollowupStatus() > 0) {
						follow.setSuccessFollowupCount(1);
					} else if (record.getFollowupStatus() < 0) {
						follow.setRejectFollowupCount(1);
					} else {
						follow.setLostFollowupCount(1);
					}

					followupMapper.saveFollowup(follow);
				} else {
					if (record.getFollowupStatus() > 0) {
						follow.setSuccessFollowupCount(follow.getSuccessFollowupCount() + 1);
					} else if (record.getFollowupStatus() < 0) {
						follow.setRejectFollowupCount(follow.getRejectFollowupCount() + 1);
					} else {
						follow.setLostFollowupCount(follow.getLostFollowupCount() + 1);
					}
					follow.setLastFollowupDate(record.getFollowupDate());
					follow.setFollowupTotalCount(follow.getFollowupTotalCount() + 1);
					followupMapper.updateFollowup(follow);
				}
			}

			return true;
		} catch (Exception e) {
			logger.error("Exception occurred during add follow-up record: ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return false;
	}

	@Override
	@Transactional
	public boolean updateFollowupRecord(FollowupRecord record) {
		try {
			FollowupRecord followupRecord = followupRecordMapper.findFollowupRecordById(record.getId());
			int affect = followupRecordMapper.updateFollowupRecord(record);
			if (affect < 1) {
				throw new TransactionSystemException("follow-up record update failed.");
			}

			if (followupRecord.getFollowupStatus().intValue() != record.getFollowupStatus().intValue()) {
				synchronized (followupMapper) {
					Followup follow = followupMapper.findFollowupBySpecimenNo(record.getSpecimenNo());

					if (followupRecord.getFollowupStatus() > 0 && follow.getSuccessFollowupCount() > 0) {
						follow.setSuccessFollowupCount(follow.getSuccessFollowupCount() - 1);
					} else if (followupRecord.getFollowupStatus() < 0 && follow.getRejectFollowupCount() > 0) {
						follow.setRejectFollowupCount(follow.getRejectFollowupCount() - 1);
					} else {
						if (follow.getLostFollowupCount() > 0)
							follow.setLostFollowupCount(follow.getLostFollowupCount() - 1);
					}
					if (record.getFollowupStatus() > 0) {
						follow.setSuccessFollowupCount(follow.getSuccessFollowupCount() + 1);
					} else if (record.getFollowupStatus() < 0) {
						follow.setRejectFollowupCount(follow.getRejectFollowupCount() + 1);
					} else {
						follow.setLostFollowupCount(follow.getLostFollowupCount() + 1);
					}
					followupMapper.updateFollowup(follow);
				}
			}

			return affect > 0;
		} catch (MapperException e) {
			logger.error("Exception occurred during update follow-up record: ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return false;
	}

	@Override
	public FollowupRecord queryFollowupRecord(long id) {
		try {
			return followupRecordMapper.findFollowupRecordById(id);
		} catch (MapperException e) {
			logger.error("Exception occurred during query follow-up record: ", e);
		}

		return null;
	}

	@Override
	public List<FollowupRecord> queryFollowupRecordForList(String specimenNo) {
		try {
			return followupRecordMapper.findFollowupRecordForList(specimenNo);
		} catch (MapperException e) {
			logger.error("Exception occurred during query follow-up record for list: ", e);
		}

		return null;
	}

	@Override
	public Followup queryFollowup(long id) {
		try {
			return followupMapper.findFollowupById(id);
		} catch (MapperException e) {
			logger.error("Exception occurred during query follow-up by id: ", e);
		}
		return null;
	}

	@Override
	public Followup queryFollowup(String specimenNo) {
		try {
			return followupMapper.findFollowupBySpecimenNo(specimenNo);
		} catch (MapperException e) {
			logger.error("Exception occurred during query follow-up by specimenNo: ", e);
		}
		return null;
	}

	@Override
	public PageInfo<Followup> queryFollowupForPagingList(FollowupQuery query) {
		try {
			PageHelper.offsetPage(query.getOffset(), query.getLimit(), query.isCount());
			return followupMapper.findFollowupForList(query).toPageInfo();
		} catch (MapperException e) {
			logger.error("Exception occurred during query follow-up for paging list: ", e);
		}

		return new PageInfo<Followup>();
	}

}
