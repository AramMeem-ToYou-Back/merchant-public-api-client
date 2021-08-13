/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.authentication.impl;

import com.am.api.orders.client.example.api.CatalogApiClient;
import com.am.api.orders.client.example.authentication.AccessTokenService;
import com.am.rest.client.catalog.ApiException;
import com.am.rest.client.catalog.api.MerchantUserResourceApi;
import com.am.rest.client.catalog.model.AuthTokensDto;
import com.am.rest.client.catalog.model.LoginData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class MerchantAccessTokenService implements AccessTokenService {

    private final String login;
    private final String password;
    private final MerchantUserResourceApi merchantUserResourceApi;
    private final RetryTemplate retryTemplate;

    private AccessTokenDto accessTokenDto;

    public MerchantAccessTokenService(@Value("${merchant.login}") String login,
                                      @Value("${merchant.password}") String password,
                                      CatalogApiClient catalogApiClient,
                                      @Qualifier("apiCallRetryTemplate") RetryTemplate retryTemplate) {
        this.login = login;
        this.password = password;
        this.merchantUserResourceApi = new MerchantUserResourceApi(catalogApiClient);
        this.retryTemplate = retryTemplate;
    }


    private static AccessTokenDto toAccessTokenDto(AuthTokensDto authTokensDto) {
        // Strip "Bearer " prefix as it substituted by ClientApi automatically
        final String authToken = authTokensDto.getAuthToken().substring("Bearer ".length());
        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setAuthToken(authToken);
        accessTokenDto.setAuthExpirationDate(authTokensDto.getAuthExpirationDate());
        accessTokenDto.setAuthExpirationPeriod(authTokensDto.getAuthExpirationPeriod());
        accessTokenDto.setRefreshToken(authTokensDto.getRefreshToken());
        accessTokenDto.setRefreshExpirationDate(authTokensDto.getRefreshExpirationDate());
        accessTokenDto.setRefreshExpirationPeriod(authTokensDto.getRefreshExpirationPeriod());
        return accessTokenDto;
    }

    @Override
    public synchronized String getAccessToken() {
        if (accessTokenDto == null) {
            accessTokenDto = getAccessTokenByLogin();
        } else {
            final Duration expirationTimeLeft = Duration.between(
                    Instant.now(),
                    Instant.ofEpochSecond(accessTokenDto.getAuthExpirationDate()));
            if (expirationTimeLeft.minusSeconds(10).isNegative()) {
                //less than 10 seconds left
                accessTokenDto = refreshToken();
            }
        }
        return accessTokenDto.getAuthToken();
    }

    private AccessTokenDto getAccessTokenByLogin() {
        final LoginData loginData = new LoginData();
        loginData.setEmail(login);
        loginData.setPassword(password);
        final AuthTokensDto authTokensDto = callLoginApi(loginData);

        return toAccessTokenDto(authTokensDto);
    }

    private AuthTokensDto callLoginApi(LoginData loginData) {
        try {
            return retryTemplate.execute(context -> merchantUserResourceApi.getTokensUsingPOST(loginData));
        } catch (ApiException e) {
            throw new RuntimeException("API responded with unexpected error", e);
        }
    }

    private AccessTokenDto refreshToken() {
        try {
            final AuthTokensDto authTokensDto = retryTemplate.execute(context -> merchantUserResourceApi.getTokensUsingGET(accessTokenDto.getRefreshToken()));
            return toAccessTokenDto(authTokensDto);
        } catch (ApiException e) {
            throw new RuntimeException("API responded with unexpected error", e);
        }
    }


}
