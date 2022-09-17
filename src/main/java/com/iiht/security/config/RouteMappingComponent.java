package com.iiht.security.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;
import com.iiht.security.service.ApiKeyService;
import com.iiht.security.util.AppConstants;

@Component
public class RouteMappingComponent {

	@Autowired
	private ApiKeyService apiKeyService;

	// mapping apikey with serviceKeys and inserting
	@PostConstruct
	public void initKeysToMongo() throws MongoDBException {
		ApiKey apiKey = new ApiKey();
		apiKey.setKey("343C-ED0B-4137-B27E");
		apiKey.setServices(Stream.of(AppConstants.SELLER_SERVICE_KEY, AppConstants.BUYER_SERVICE_KEY)
				.collect(Collectors.toList()));
		apiKeyService.insertApiKeyData(apiKey);
		
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route(AppConstants.SELLER_SERVICE_KEY,
				r -> r.path("/e-auction/api/v1/seller/**").filters(f -> f.stripPrefix(4)).uri("http://localhost:8081"))
				.route(AppConstants.BUYER_SERVICE_KEY, r -> r.path("/e-auction/api/v1/buyer/**")
						.filters(f -> f.stripPrefix(4)).uri("http://localhost:8082"))
				.build();
	}
}
