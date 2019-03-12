package com.gb.chrom.model;

/**
 * 标本序列号规则
 * 
 * @since 1.0
 * @author Summer
 */
public class SerialNumberRule implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;

	/** 是否需要类型表示符 */
	private Boolean isTypeHead = false;

	/** 时间格式 */
	private String timeFormat;

	/** 单/双线类型 */
	private String lineType;

	/** 随机数长度 */
	private Integer snLength;

	/** 默认单线打印数量 */
	private Integer linePrintCount;

	/**
	 * @return the {@link #id}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the {@link #id} to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the {@link #isTypeHead}
	 */
	public Boolean getIsTypeHead() {
		return isTypeHead;
	}

	/**
	 * @param isTypeHead
	 *            the {@link #isTypeHead} to set
	 */
	public void setIsTypeHead(Boolean isTypeHead) {
		this.isTypeHead = isTypeHead;
	}

	/**
	 * @return the {@link #timeFormat}
	 */
	public String getTimeFormat() {
		return timeFormat;
	}

	/**
	 * @param timeFormat
	 *            the {@link #timeFormat} to set
	 */
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	/**
	 * @return the {@link #lineType}
	 */
	public String getLineType() {
		return lineType;
	}

	/**
	 * @param lineType
	 *            the {@link #lineType} to set
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the {@link #snLength}
	 */
	public Integer getSnLength() {
		return snLength;
	}

	/**
	 * @param snLength
	 *            the {@link #snLength} to set
	 */
	public void setSnLength(Integer snLength) {
		this.snLength = snLength;
	}

	/**
	 * @return the {@link #linePrintCount}
	 */
	public Integer getLinePrintCount() {
		return linePrintCount;
	}

	/**
	 * @param linePrintCount the {@link #linePrintCount} to set
	 */
	public void setLinePrintCount(Integer linePrintCount) {
		this.linePrintCount = linePrintCount;
	}

	@Override
	public String toString() {
		return new StringBuilder(SerialNumberRule.class.getSimpleName()).append(" {").append("\n\t id=").append(id).append(",\n\t isTypeHead=").append(isTypeHead)
				.append(",\n\t timeFormat=").append(timeFormat).append(",\n\t lineType=").append(lineType).append(",\n\t snLength=").append(snLength)
				.append(",\n\t linePrintCount=").append(linePrintCount).append("\n}").toString();
	}

}
