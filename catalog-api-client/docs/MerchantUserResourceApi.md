# MerchantUserResourceApi

All URIs are relative to *//https://sand.toyou.delivery/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTokensUsingGET**](MerchantUserResourceApi.md#getTokensUsingGET) | **GET** /catalog/v1/merchantuser/authtoken | Get refreshed tokens
[**getTokensUsingPOST**](MerchantUserResourceApi.md#getTokensUsingPOST) | **POST** /catalog/v1/merchantuser/authtoken | Generate tokens for existing merchant user by email and password

<a name="getTokensUsingGET"></a>
# **getTokensUsingGET**
> AuthTokensDto getTokensUsingGET(authorization)

Get refreshed tokens

### Example
```java
// Import classes:
//import com.am.rest.client.catalog.ApiException;
//import com.am.rest.client.catalog.api.MerchantUserResourceApi;


MerchantUserResourceApi apiInstance = new MerchantUserResourceApi();
String authorization = "authorization_example"; // String | Refresh token
try {
    AuthTokensDto result = apiInstance.getTokensUsingGET(authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MerchantUserResourceApi#getTokensUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **String**| Refresh token | [optional]

### Return type

[**AuthTokensDto**](AuthTokensDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTokensUsingPOST"></a>
# **getTokensUsingPOST**
> AuthTokensDto getTokensUsingPOST(body)

Generate tokens for existing merchant user by email and password

### Example
```java
// Import classes:
//import com.am.rest.client.catalog.ApiException;
//import com.am.rest.client.catalog.api.MerchantUserResourceApi;


MerchantUserResourceApi apiInstance = new MerchantUserResourceApi();
LoginData body = new LoginData(); // LoginData | Login data object
try {
    AuthTokensDto result = apiInstance.getTokensUsingPOST(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MerchantUserResourceApi#getTokensUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**LoginData**](LoginData.md)| Login data object |

### Return type

[**AuthTokensDto**](AuthTokensDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

