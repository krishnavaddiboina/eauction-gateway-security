package com.iiht.security.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;
import com.iiht.security.repository.ApiKeyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiKeyRepositoryImpl implements ApiKeyRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void insertApiKeyData(ApiKey apiKey) throws MongoDBException {
		try {
			mongoTemplate.save(apiKey);
		} catch (Exception exception) {
			log.error("Error occured while saving api key data. Error is {}", exception.getMessage());
			throw new MongoDBException("Error occured while saving api key data in mongo db");
		}
	}

	public ApiKey getApiKeyData(String key) throws MongoDBException {
		ApiKey apiKey = null;
		try {
		Query query = new Query(Criteria.where("key").is(key));
		apiKey = mongoTemplate.find(query, ApiKey.class).get(0);
		}catch(Exception exception) {
			log.error("Error occured while getting apikey details. Error is {}", exception.getMessage());
			throw new MongoDBException("Error occure while getting apikey details in mongo db");
		}
		return apiKey;
	}
}
