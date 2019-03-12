package com.gb.chrom.constants;

/**
 * 标本状态
 * 
 * @author Summer
 *
 *         2018年7月31日
 */
public interface SpecimenStatus {

	int STATUS_OF_DEFAULT = 0;

	// Specimen Culture Process

	/** 培养 */
	int STATUS_OF_CULTURE = 1;

	/** 收获 */
	int STATUS_OF_CULTURE_HARVEST = 2;

	/** 滴信 */
	int STATUS_OF_CULTURE_DROP = 3;

	/** 显带 */
	int STATUS_OF_CULTURE_BANDING = 4;

	/** 扫片 */
	int STATUS_OF_CULTURE_SCAN = 5;
	
	int STATUS_OF_CULTURE_COMPLETED = 5;

	// 核型分析

	/** 完成核型分析 */
	int STATUS_OF_KARYOTYPE_ANALYSED = 10;

	/** 提交分析报告 */
	int STATUS_OF_ANALYSIS_REPORTED = 15;

	/** 分析报告审核通过 */
	int STATUS_OF_ANALYSIS_REPORT_AUDIT = 20;
	
	
	/* ******************* 标本分析报告审核状态 **************************/

	/** 等待审核 */
	String STATUS_OF_AUDIT_WAITING = "WAIT_AUDIT";
	
	/** 审核驳回 */
	String STATUS_OF_AUDIT_REJECT = "AUDIT_REJECT";
	
	/** 审核通过 */
	String STATUS_OF_AUDIT_PASSRD = "AUDIT_PASSED";
	
}
