package com.todd.nio.utils.annotationis;

public class FieldError {
	
	/**
	 * 返回码
	 */
	private String returnCode;
	
	/**
	 * 返回描述
	 */
	private String description;

	public FieldError(String returnCode, String description) {
		this.returnCode = returnCode;
		this.description = description;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
