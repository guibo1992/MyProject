package com.gb.chrom.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 试剂
 * <p>
 * 
 * @author Summer
 * 
 *         Created by 2018年4月23日
 * @since
 */
public class Reagent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 名称 */
	private String name;

	/** 类型 */
	private String type;

	/** 规格 */
	private String spec;

	/** 数量单位 */
	private String qtyUnit;

	/** 库存量 */
	private Long stocks;

	/** 预警库存 */
	private Long warnStock;

	/** 存储条件 */
	private String storeCondition;

	/** 备注 */
	private String remark;

	/** 最后入库时间 (与入库单时间一致) */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date lastPutInTime;

	/** 最后出库时间 (与出库单时间一致) */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date lastPutOutTime;

	/** 创建时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtCreated;

	/**
	 * @return the {@link #id}
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the {@link #id} to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #type}
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the {@link #type} to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the {@link #spec}
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * @param spec
	 *            the {@link #spec} to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return the {@link #qtyUnit}
	 */
	public String getQtyUnit() {
		return qtyUnit;
	}

	/**
	 * @param qtyUnit
	 *            the {@link #qtyUnit} to set
	 */
	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}

	/**
	 * @return the {@link #stocks}
	 */
	public Long getStocks() {
		return stocks;
	}

	/**
	 * @param stocks
	 *            the {@link #stocks} to set
	 */
	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}

	/**
	 * @return the {@link #warnStock}
	 */
	public Long getWarnStock() {
		return warnStock;
	}

	/**
	 * @param warnStock
	 *            the {@link #warnStock} to set
	 */
	public void setWarnStock(Long warnStock) {
		this.warnStock = warnStock;
	}

	/**
	 * @return the {@link #storeCondition}
	 */
	public String getStoreCondition() {
		return storeCondition;
	}

	/**
	 * @param storeCondition
	 *            the {@link #storeCondition} to set
	 */
	public void setStoreCondition(String storeCondition) {
		this.storeCondition = storeCondition;
	}

	/**
	 * @return the {@link #remark}
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the {@link #remark} to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the {@link #lastPutInTime}
	 */
	public Date getLastPutInTime() {
		return lastPutInTime;
	}

	/**
	 * @param lastPutInTime
	 *            the {@link #lastPutInTime} to set
	 */
	public void setLastPutInTime(Date lastPutInTime) {
		this.lastPutInTime = lastPutInTime;
	}

	/**
	 * @return the {@link #lastPutOutTime}
	 */
	public Date getLastPutOutTime() {
		return lastPutOutTime;
	}

	/**
	 * @param lastPutOutTime
	 *            the {@link #lastPutOutTime} to set
	 */
	public void setLastPutOutTime(Date lastPutOutTime) {
		this.lastPutOutTime = lastPutOutTime;
	}

	/**
	 * @return the {@link #gmtCreated}
	 */
	public Date getGmtCreated() {
		return gmtCreated;
	}

	/**
	 * @param gmtCreated
	 *            the {@link #gmtCreated} to set
	 */
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}

}
