package com.gb.chrom.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 打印模板
 * 
 * @since 1.0
 * @author Summer
 */
public class SlideTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 标识ID */
	private String idCode;

	/** 名称 */
	private String name;

	/** 宽 */
	private Float width;

	/** 高 */
	private Float height;

	/** html内容 */
	private String htmlContent;

	/** 状态 */
	private Boolean status;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtModified;

	private Map<String, SlideTemplateItem> itemMap = new HashMap<>();

	private List<SlideTemplateItem> itemList;

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
	 * @return the {@link #idCode}
	 */
	public String getIdCode() {
		return idCode;
	}

	/**
	 * @param idCode
	 *            the {@link #idCode} to set
	 */
	public void setIdCode(String idCode) {
		this.idCode = idCode;
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
	 * @return the {@link #width}
	 */
	public Float getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the {@link #width} to set
	 */
	public void setWidth(Float width) {
		this.width = width;
	}

	/**
	 * @return the {@link #height}
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the {@link #height} to set
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * @return the {@link #htmlContent}
	 */
	public String getHtmlContent() {
		return htmlContent;
	}

	/**
	 * @param htmlContent
	 *            the {@link #htmlContent} to set
	 */
	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	/**
	 * @return the {@link #status}
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the {@link #status} to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the {@link #gmtModified}
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 *            the {@link #gmtModified} to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return the {@link #itemList}
	 */
	public List<SlideTemplateItem> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList
	 *            the {@link #itemList} to set
	 */
	public void setItemList(List<SlideTemplateItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the {@link #itemMap}
	 */
	public Map<String, SlideTemplateItem> getItemMap() {
		return itemMap;
	}

	/**
	 * @param itemMap
	 *            the {@link #itemMap} to set
	 */
	public void setItemMap(Map<String, SlideTemplateItem> itemMap) {
		this.itemMap = itemMap;
	}

	@Override
	public String toString() {
		return new StringBuilder(SlideTemplate.class.getSimpleName()).append(" {").append("\n\t id=").append(id).append(",\n\t idCode=").append(idCode).append(",\n\t name=")
				.append(name).append(",\n\t width=").append(width).append(",\n\t height=").append(height).append(",\n\t htmlContent=").append(htmlContent).append(",\n\t status=")
				.append(status).append(",\n\t gmtModified=").append(gmtModified).append("\n}").toString();
	}

}
