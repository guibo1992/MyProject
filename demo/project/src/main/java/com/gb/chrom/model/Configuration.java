package com.gb.chrom.model;

import javax.validation.constraints.NotBlank;

/**
 * 系统配置
 * 
 * @since 1.0
 * @author Summer
 */
public class Configuration implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 系统标识码 */
	@NotBlank
	private String idCode;

	/** 医院名称 */
	@NotBlank
	private String hospName;

	/** 医院简称 */
	@NotBlank
	private String hospAliase;

	/** logo url */
	private String hospLogo;

//	/** backgroup image url */
//	private String background;

	/* *************** Printer Config ******************** */

	/** 载玻片打印机 */
	private String slidePrinter;

	/** 条形码标签打印机 */
	private String barcodePrinter;

	/** 分析报告打印机 */
	private String reportPrinter;

	/* *************** SMB Share Server Config ******************** */

	/** SMB Server IP */
	private String smbDomain;

	/** NTLM username */
	private String ntlmUsername;

	/** NTLM password */
	private String ntlmPassword;

	/** SBM Server share source path */
	// @Pattern(regexp = "^(/[a-zA-Z0-9]+){2,}$")
	private String shareSource;

	/**
	 * @return the {@link #id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the {@link #id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the {@link #idCode}
	 */
	public String getIdCode() {
		return idCode;
	}

	/**
	 * @param idCode the {@link #idCode} to set
	 */
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	/**
	 * @return the {@link #hospName}
	 */
	public String getHospName() {
		return hospName;
	}

	/**
	 * @param hospName the {@link #hospName} to set
	 */
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	/**
	 * @return the {@link #hospAliase}
	 */
	public String getHospAliase() {
		return hospAliase;
	}

	/**
	 * @param hospAliase the {@link #hospAliase} to set
	 */
	public void setHospAliase(String hospAliase) {
		this.hospAliase = hospAliase;
	}

	/**
	 * @return the {@link #smbDomain}
	 */
	public String getSmbDomain() {
		return smbDomain;
	}

	/**
	 * @param smbDomain the {@link #smbDomain} to set
	 */
	public void setSmbDomain(String smbDomain) {
		this.smbDomain = smbDomain;
	}

	/**
	 * @return the {@link #hospLogo}
	 */
	public String getHospLogo() {
		return hospLogo;
	}

	/**
	 * @param hospLogo the {@link #hospLogo} to set
	 */
	public void setHospLogo(String hospLogo) {
		this.hospLogo = hospLogo;
	}

	/**
	 * @return the {@link #slidePrinter}
	 */
	public String getSlidePrinter() {
		return slidePrinter;
	}

	/**
	 * @param slidePrinter the {@link #slidePrinter} to set
	 */
	public void setSlidePrinter(String slidePrinter) {
		this.slidePrinter = slidePrinter;
	}

	/**
	 * @return the {@link #barcodePrinter}
	 */
	public String getBarcodePrinter() {
		return barcodePrinter;
	}

	/**
	 * @param barcodePrinter the {@link #barcodePrinter} to set
	 */
	public void setBarcodePrinter(String barcodePrinter) {
		this.barcodePrinter = barcodePrinter;
	}

	/**
	 * @return the {@link #reportPrinter}
	 */
	public String getReportPrinter() {
		return reportPrinter;
	}

	/**
	 * @param reportPrinter the {@link #reportPrinter} to set
	 */
	public void setReportPrinter(String reportPrinter) {
		this.reportPrinter = reportPrinter;
	}

	/**
	 * @return the {@link #ntlmUsername}
	 */
	public String getNtlmUsername() {
		return ntlmUsername;
	}

	/**
	 * @param ntlmUsername the {@link #ntlmUsername} to set
	 */
	public void setNtlmUsername(String ntlmUsername) {
		this.ntlmUsername = ntlmUsername;
	}

	/**
	 * @return the {@link #ntlmPassword}
	 */
	public String getNtlmPassword() {
		return ntlmPassword;
	}

	/**
	 * @param ntlmPassword the {@link #ntlmPassword} to set
	 */
	public void setNtlmPassword(String ntlmPassword) {
		this.ntlmPassword = ntlmPassword;
	}

	/**
	 * @return the {@link #shareSource}
	 */
	public String getShareSource() {
		return shareSource;
	}

	/**
	 * @param shareSource the {@link #shareSource} to set
	 */
	public void setShareSource(String shareSource) {
		this.shareSource = shareSource;
	}

}
