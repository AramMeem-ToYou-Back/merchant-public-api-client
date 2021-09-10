/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.api;

import com.am.rest.client.catalog.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CatalogApiClient extends ApiClient {

    public CatalogApiClient(
            @Value("${toyou.api.protocol}") String protocol,
            @Value("${toyou.api.host}") String host,
            @Value("${toyou.api.port}") int port) {
        super();
        String basePath = String.format("%s://%s:%d", protocol, host, port);
        setBasePath(basePath);
    }
}
