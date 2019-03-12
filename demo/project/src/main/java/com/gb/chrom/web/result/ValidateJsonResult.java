package com.gb.chrom.web.result;

/**
 * @author Summer
 *
 *         Created by 2016年10月21日
 */
public class ValidateJsonResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** validate result */
	private boolean valid;

	public ValidateJsonResult(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the {@link #valid}
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the {@link #valid} to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * valid validate result
	 * 
	 * @return
	 */
	public static ValidateJsonResult valid() {
		return new ValidateJsonResult(true);
	}

	/**
	 * Invalid validate result
	 * 
	 * @return
	 */
	public static ValidateJsonResult invalid() {
		return new ValidateJsonResult(false);
	}

	/**
	 * @return validate result
	 */
	public static ValidateJsonResult valid(boolean valid) {
		return new ValidateJsonResult(valid);
	}

}
