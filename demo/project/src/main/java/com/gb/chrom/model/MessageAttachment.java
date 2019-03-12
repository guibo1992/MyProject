package com.gb.chrom.model;

/**
 * @author Summer
 *
 *         2018年8月2日
 */
public class MessageAttachment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long messageId;

	/** 名称 */
	private String name;

	/** 文件 */
	private String file;

	/** 文件类型 */
	private String type;

	/** 文件大小 */
	private Long filesize;

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
	 * @return the {@link #messageId}
	 */
	public Long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the {@link #messageId} to set
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the {@link #file}
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param file the {@link #file} to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the {@link #type}
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the {@link #filesize}
	 */
	public Long getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the {@link #filesize} to set
	 */
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

}
