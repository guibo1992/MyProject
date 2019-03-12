package com.gb.chrom.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Summer
 *
 */
public class Paginator implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public enum Direction {
		/** 升序 */
		ASC,
		/** 降序 */
		DESC
	}
	
	/** 每页最大条目数 */
	public static final int MAX_PAGE_SIZE = Integer.MAX_VALUE;
	
	public static final String PREFIX_ORDER_BY = " ORDER BY ";

	/** 默认offset */
	private static final int DEFAULT_OFFSET = 0;

	/** 默认offset */
	private static final int DEFAULT_LIMIT = 10;

	/** start rows */
	protected int offset = DEFAULT_OFFSET;

	/** as page size */
	protected int limit = DEFAULT_LIMIT;

	/** by column name */
	protected String sort;

	/** ASC, DESC */
	protected String order = Direction.DESC.name();

	/** current page number */
	private int pageNumber;

	/** 是否进行count查询 */
	private boolean count = Boolean.TRUE;

	/**
	 * search text equals keyword text
	 * <p>
	 * for use extends
	 * </p>
	 * 
	 * 
	 */
	private String search;

	/**
	 * search keyword equals search text
	 * <p>
	 * if is null return search
	 * </p>
	 */
	private String keyword;

	/**
	 * @return the {@link #offset}
	 */
	public int getOffset() {
		if (offset < 0) {
			offset = DEFAULT_OFFSET;
		}

		return offset;
	}

	/**
	 * @param offset
	 *            the {@link #offset} to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the {@link #limit}
	 */
	public int getLimit() {
		if (limit < 1) {
			limit = MAX_PAGE_SIZE;
		}

		return limit;
	}

	/**
	 * @param limit
	 *            the {@link #limit} to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the {@link #pageNumber}
	 */
	public int getPageNumber() {
		if (pageNumber < 1) {
			pageNumber = (getOffset() / getLimit()) + 1;
		}

		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            the {@link #pageNumber} to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the {@link #sort}
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the {@link #sort} to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the {@link #order}
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * order by SQL
	 * 
	 * @return
	 */
	public String getOrderBy() {
		if (StringUtils.isBlank(this.sort)) {
			return null;
		}

		StringBuilder builder = new StringBuilder();
		if (!StringUtils.isBlank(this.order)) {
			builder.append(PREFIX_ORDER_BY).append(sort.toUpperCase()).append(" ").append(order.toUpperCase());
		}

		return builder.toString();
	}

	/**
	 * @param order
	 *            the {@link #order} to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the {@link #search}
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the {@link #search} to set
	 */
	public void setSearch(String search) {
		this.search = search;
		this.keyword = search;
	}

	/**
	 * @return the {@link #keyword}
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the {@link #keyword} to set
	 */
	public void setKeyword(String keyword) {
		this.search = keyword;
		this.keyword = keyword;
	}

	/**
	 * @return the {@link #count}
	 */
	public boolean isCount() {
		return count;
	}

	/**
	 * @param count
	 *            the {@link #count} to set
	 */
	public void setCount(boolean count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return new StringBuilder(Paginator.class.getSimpleName()).append(": {\n\t").append("limit: ").append(limit).append(",\n\t").append("offset: ")
				.append(offset).append(",\n\t").append("search: ").append(search).append(",\n\t").append("sort: ").append(sort).append(",\n\t")
				.append("order: ").append(order).append("\n\t").append("count: ").append(count).append("\n").append("}").toString();
	}

}
