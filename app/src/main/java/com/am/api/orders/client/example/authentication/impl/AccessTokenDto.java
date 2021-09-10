/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.authentication.impl;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String authToken;
    private String refreshToken;
    private long authExpirationDate;
    private long refreshExpirationDate;
    private long authExpirationPeriod;
    private long refreshExpirationPeriod;
}
