package com.gb.chrom.core;

/**
 * DAO 异常类
 */
public class MapperException extends Exception {

	/*** serial id */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * 空构造
	 */
	public MapperException() {
		super("Dao 异常");
	}
	
	/**
	 * 
	 * 自定义错误日志
	 * 
	 * @param e
	 */
	public MapperException(String e) {
		super(e);
	}

	/**
	 * 只抛错误信息
	 * 
	 * @param e
	 */
	public MapperException(Throwable e) {
		super(e);
	}

	/**
	 * 两者皆抛
	 * 
	 * @param er
	 * @param e
	 */
	public MapperException(String er, Throwable e) {
		super(er, e);
	}
}
