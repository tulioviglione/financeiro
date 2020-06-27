package com.financeiro.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financeiro.api.exceptions.BusinessException;

public final class FunctionUtil {

	private FunctionUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String asJsonString(final Object obj) throws BusinessException {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new BusinessException(e.getMessage(),e);
	    }
	}
}
