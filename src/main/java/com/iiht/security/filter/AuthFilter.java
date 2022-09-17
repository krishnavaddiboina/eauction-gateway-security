package com.iiht.security.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.iiht.security.dto.ApiKey;
import com.iiht.security.exception.MongoDBException;
import com.iiht.security.service.ApiKeyService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 
 * @author cogjava1489
 * 
 * first request will come to this global filer then it will goes to gateway
 *
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter , Ordered {

    @Autowired
    private ApiKeyService apiKeyService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> apiKeyHeader=exchange.getRequest().getHeaders().get("gatewaykey");
        log.info("api key {} ",apiKeyHeader);
        Route route=exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId=route!=null? route.getId() : null;
        log.info("routeId {} ", routeId);
        if(routeId ==null || CollectionUtils.isEmpty(apiKeyHeader) || !isAuthorize(routeId, apiKeyHeader.get(0))){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"you can't consume this service , Please validate your apikeys");
        }return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private boolean isAuthorize(String routeId,String apiKey){
    	ApiKey apiKeyObject= null;
    	boolean flag = false;
    	try {
	        apiKeyObject=apiKeyService.getApiKeyData(apiKey);
	        
	        if(apiKeyObject!=null){            
	            flag = apiKeyObject.getServices().contains(routeId);
	        }
    	} catch (Exception exception) {
			log.error("Error occured while getting api key data in AuthenticationFilter class. Error is {}", exception.getMessage());			
		}
        return flag;
    }
}
