package com.gb.chrom.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gb.chrom.serialize.JsonDateSerializer;
import com.gb.chrom.serialize.JsonDateTimeSerializer;

/**
 * 系统消息
 * <p>
 * 
 * @author Summer
 * @since
 */
public class Message implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 标题 */
	@NotBlank
	private String title;

	/** 消息简介 */
	private String brief;

	/** 消息内容 */
	@NotBlank
	private String content;

	/** 发布日期 */
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date releaseDate;

	/** 发布人ID */
	private Long releaserId;

	/** 状态 */
	private Boolean status;

	/** 更新时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date gmtModified;

	/** 附件列表 */
	private List<MessageAttachment> attachList;

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
	 * @return the {@link #title}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the {@link #title} to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the brief
	 */
	public String getBrief() {
		if (StringUtils.isBlank(brief) && StringUtils.isNotBlank(this.content)) {
			brief = this.content.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<([^>]*)>", "");
			
			if (brief.length() > 42) {
				brief = brief.substring(0, 42) + "...";
			}
		}

		return brief;
	}

	/**
	 * @param brief the brief to set
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * @return the {@link #content}
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the {@link #content} to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the {@link #releaseDate}
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the {@link #releaseDate} to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the releaserId
	 */
	public Long getReleaserId() {
		return releaserId;
	}

	/**
	 * @param releaserId the releaserId to set
	 */
	public void setReleaserId(Long releaserId) {
		this.releaserId = releaserId;
	}

	/**
	 * @return the {@link #status}
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the {@link #status} to set
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
	 * @param gmtModified the {@link #gmtModified} to set
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return the {@link #attachList}
	 */
	public List<MessageAttachment> getAttachList() {
		return attachList;
	}

	/**
	 * @param attachList the {@link #attachList} to set
	 */
	public void setAttachList(List<MessageAttachment> attachList) {
		this.attachList = attachList;
	}

}
