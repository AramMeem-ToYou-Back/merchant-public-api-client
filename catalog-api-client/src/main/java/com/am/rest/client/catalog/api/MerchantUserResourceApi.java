package com.am.rest.client.catalog.api;

import com.am.rest.client.catalog.ApiException;
import com.am.rest.client.catalog.ApiClient;
import com.am.rest.client.catalog.Configuration;
import com.am.rest.client.catalog.Pair;

import javax.ws.rs.core.GenericType;

import com.am.rest.client.catalog.model.AuthTokensDto;
import com.am.rest.client.catalog.model.LoginData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantUserResourceApi {
  private ApiClient apiClient;

  public MerchantUserResourceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public MerchantUserResourceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get refreshed tokens
   * 
   * @param authorization Refresh token (optional)
   * @return AuthTokensDto
   * @throws ApiException if fails to make API call
   */
  public AuthTokensDto getTokensUsingGET(String authorization) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/catalog/v1/merchantuser/authtoken".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (authorization != null)
      localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<AuthTokensDto> localVarReturnType = new GenericType<AuthTokensDto>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Generate tokens for existing merchant user by email and password
   * 
   * @param body Login data object (required)
   * @return AuthTokensDto
   * @throws ApiException if fails to make API call
   */
  public AuthTokensDto getTokensUsingPOST(LoginData body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling getTokensUsingPOST");
    }
    // create path and map variables
    String localVarPath = "/catalog/v1/merchantuser/authtoken".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<AuthTokensDto> localVarReturnType = new GenericType<AuthTokensDto>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
