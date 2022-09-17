package com.iiht.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;
import com.iiht.security.repository.ApiKeyRepository;
import com.iiht.security.service.ApiKeyService;

@Service
public class ApiKeyServiceImpl implements ApiKeyService{
	
	@Autowired
	ApiKeyRepository apiKeyRepository;

	@Override
	public void insertApiKeyData(ApiKey apiKey) throws MongoDBException {
		apiKeyRepository.insertApiKeyData(apiKey);
		
	}

	@Override
	public ApiKey getApiKeyData(String key) throws MongoDBException {		
		return apiKeyRepository.getApiKeyData(key);
	}

}
