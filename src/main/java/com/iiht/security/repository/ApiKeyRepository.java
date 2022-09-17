package com.iiht.security.repository;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;

public interface ApiKeyRepository {
	
	public void insertApiKeyData(ApiKey apiKey) throws MongoDBException;

	public ApiKey getApiKeyData(String key) throws MongoDBException;

}
