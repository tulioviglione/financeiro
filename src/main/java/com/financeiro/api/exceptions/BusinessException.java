package com.financeiro.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(BusinessException.class);
	
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
        super(message);
    }

	public BusinessException(String message, Throwable e) {
		super(message, e);
		LOG.error(e.getMessage());
	}
}
