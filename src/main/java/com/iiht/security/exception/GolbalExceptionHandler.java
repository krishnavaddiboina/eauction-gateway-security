package com.iiht.security.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.iiht.security.dto.ApiKeyResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GolbalExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MongoDBException.class)
	public ResponseEntity<ApiKeyResponse> INTERNAL_SERVER_ERROR(MongoDBException exception) {
		log.error("Withing handleMongoDBException() of GolbalExceptionHandler. Error is {}", exception.getMessage());
		ApiKeyResponse apiKeyResponse = exception.getApiKeyResponse();
		apiKeyResponse.setResponseTime(new Date());
		return new ResponseEntity<ApiKeyResponse>(apiKeyResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
