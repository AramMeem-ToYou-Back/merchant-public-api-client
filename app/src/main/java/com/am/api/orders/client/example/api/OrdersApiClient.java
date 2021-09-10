/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.api;

import com.am.api.orders.client.example.authentication.AccessTokenService;
import com.am.rest.client.orders.ApiClient;
import com.am.rest.client.orders.ApiException;
import com.am.rest.client.orders.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.Map;

@Component
public class OrdersApiClient extends ApiClient {
    private final AccessTokenService accessTokenService;

    public OrdersApiClient(AccessTokenService accessTokenService,
                           @Value("${toyou.api.protocol}") String protocol,
                           @Value("${toyou.api.host}") String host,
                           @Value("${toyou.api.port}") int port) {
        super();
        String basePath = String.format("%s://%s:%d/publicorders", protocol, host, port);
        setBasePath(basePath);
        this.accessTokenService = accessTokenService;
    }

    @Override
    public <T> T invokeAPI(String path,
                           String method,
                           List<Pair> queryParams,
                           Object body,
                           Map<String, String> headerParams,
                           Map<String, Object> formParams,
                           String accept,
                           String contentType,
                           String[] authNames,
                           GenericType<T> returnType) throws ApiException {

        super.setAccessToken(accessTokenService.getAccessToken());
        return super.invokeAPI(path, method, queryParams, body, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
