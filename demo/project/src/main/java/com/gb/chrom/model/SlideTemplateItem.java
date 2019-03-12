package com.gb.chrom.model;

/**
 * 打印模板条目
 * 
 * @since 1.0
 * @author Summer
 */
public class SlideTemplateItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ID */
	private Long id;

	/** 模板ID */
	private Long tmplId;

	/** 类型, hosp,name,date,sn,qr */
	private String type;

	/** 文本值 */
	private String value;

	/** 字体大小 */
	private Float fontSize;

	/** 位置 X */
	private Float pointX;

	/** 位置 */
	private Float pointY;

	/** width */
	private Float width;

	/** height */
	private Float height;

	/** padding */
	private Integer padding;

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
	 * @return the {@link #tmplId}
	 */
	public Long getTmplId() {
		return tmplId;
	}

	/**
	 * @param tmplId
	 *            the {@link #tmplId} to set
	 */
	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
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
	 * @return the {@link #value}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the {@link #value} to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the {@link #fontSize}
	 */
	public Float getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            the {@link #fontSize} to set
	 */
	public void setFontSize(Float fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return the {@link #pointX}
	 */
	public Float getPointX() {
		return pointX;
	}

	/**
	 * @param pointX
	 *            the {@link #pointX} to set
	 */
	public void setPointX(Float pointX) {
		this.pointX = pointX;
	}

	/**
	 * @return the {@link #pointY}
	 */
	public Float getPointY() {
		return pointY;
	}

	/**
	 * @param pointY
	 *            the {@link #pointY} to set
	 */
	public void setPointY(Float pointY) {
		this.pointY = pointY;
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
	 * @return the {@link #padding}
	 */
	public Integer getPadding() {
		return padding;
	}

	/**
	 * @param padding
	 *            the {@link #padding} to set
	 */
	public void setPadding(Integer padding) {
		this.padding = padding;
	}

	@Override
	public String toString() {
		return new StringBuilder(SlideTemplateItem.class.getSimpleName()).append(" {").append("\n\t id=").append(id).append("\n\t tmplId=").append(tmplId).append("\n\t type=")
				.append(type).append(",\n\t value=").append(value).append(",\n\t fontSize=").append(fontSize).append(",\n\t pointX=").append(pointX).append(",\n\t pointY=")
				.append(pointY).append(",\n\t width=").append(width).append(",\n\t height=").append(height).append("\n}").toString();
	}

}
