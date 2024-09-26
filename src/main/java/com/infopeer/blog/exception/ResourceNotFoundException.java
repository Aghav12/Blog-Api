package com.infopeer.blog.exception;

import com.infopeer.blog.constant.ApiConstants;

public class ResourceNotFoundException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format(ApiConstants.NOT_FOUND, fieldName, fieldValue, resourceName));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
