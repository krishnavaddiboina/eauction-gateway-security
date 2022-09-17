package com.iiht.security.service;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;

public interface ApiKeyService {
	public void insertApiKeyData(ApiKey apiKey) throws MongoDBException;

	public ApiKey getApiKeyData(String key) throws MongoDBException;
}
