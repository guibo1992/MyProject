package com.gb.chrom.web.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.gb.chrom.domain.Paginator;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * Bootstrap table server paginator json result
 * </p>
 * <code>
 * sidePagination: 'server'
 * </code>
 * 
 * @author Summer
 *
 */
public class PaginatorJsonResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long total;

	private List<?> rows;
	
	private String search;
	
	private int offset;
	
	private int limit;

	private Map<String, Object> data = new HashMap<String, Object>();

	public PaginatorJsonResult(long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	/**
	 * @return the {@link #total}
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the {@link #total} to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @return the {@link #rows}
	 */
	public List<?> getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the {@link #rows} to set
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}


	/**
	 * @return the {@link #search}
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the {@link #search} to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * @return the {@link #offset}
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the {@link #offset} to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the {@link #limit}
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the {@link #limit} to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the {@link #data}
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * @param data the {@link #data} to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public PaginatorJsonResult putData(String key, Object value) {
		if (MapUtils.isEmpty(data)) {
			data = new HashMap<String, Object>();
		}
		data.put(key, value);
		
		return this;
	}

	public static PaginatorJsonResult getPaginatorResult(long total, List<?> list) {
		return new PaginatorJsonResult(total, list);
	}

	public static PaginatorJsonResult getPaginatorResult(PageInfo<?> pageInfo) {
		return new PaginatorJsonResult(pageInfo.getTotal(), pageInfo.getList());
	}
	
	public static PaginatorJsonResult getPaginatorResult(PageInfo<?> pageInfo, Paginator paginator) {
		PaginatorJsonResult result = new PaginatorJsonResult(pageInfo.getTotal(), pageInfo.getList());
		result.setLimit(paginator.getLimit());
		result.setOffset(paginator.getOffset());
		result.setSearch(paginator.getSearch());
		
		return result;
	}

}
