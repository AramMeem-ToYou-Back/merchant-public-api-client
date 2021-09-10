/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.authentication;

public interface AccessTokenService {
    /**
     * Get API access token
     *
     * @return bearer value
     */
    String getAccessToken();
}
