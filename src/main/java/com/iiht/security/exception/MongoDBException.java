package com.iiht.security.exception;

import org.springframework.http.HttpStatus;

import com.iiht.security.dto.ApiKeyResponse;

public class MongoDBException extends Exception {
	
	private static final long serialVersionUID = -2044727719943570506L;
	private String message;
	private ApiKeyResponse apiKeyResponse;

	public MongoDBException() {
		super();
	}

	public MongoDBException(String message) {
		super(message);
		this.message = message;		
		apiKeyResponse.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		apiKeyResponse.setMessage(message);		
	}

	public String getMessage() {
		return message;
	}

	public ApiKeyResponse getApiKeyResponse() {
		return apiKeyResponse;
	}

}
