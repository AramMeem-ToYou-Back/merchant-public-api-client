# OrdersResourceApi

All URIs are relative to *https://sand.toyou.delivery/publicorders*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orderByIdByUsingGET**](OrdersResourceApi.md#orderByIdByUsingGET) | **GET** /v1/orders/{order-id} | Get Order data by ID
[**orderListByUsingGET**](OrdersResourceApi.md#orderListByUsingGET) | **GET** /v1/orders | Get Order change data by date time period
[**setOrderFailedUsingPUT**](OrdersResourceApi.md#setOrderFailedUsingPUT) | **POST** /v1/orders/{order-id}/failed | Set Order as Failed

<a name="orderByIdByUsingGET"></a>
# **orderByIdByUsingGET**
> OrderData orderByIdByUsingGET(orderId)

Get Order data by ID

Use Order ID from order change data to get Order details

### Example
```java
// Import classes:
//import com.am.rest.client.orders.ApiClient;
//import com.am.rest.client.orders.ApiException;
//import com.am.rest.client.orders.Configuration;
//import com.am.rest.client.orders.auth.*;
//import com.am.rest.client.orders.api.OrdersResourceApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


OrdersResourceApi apiInstance = new OrdersResourceApi();
UUID orderId = new UUID(); // UUID | Order ID
try {
    OrderData result = apiInstance.orderByIdByUsingGET(orderId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OrdersResourceApi#orderByIdByUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | [**UUID**](.md)| Order ID |

### Return type

[**OrderData**](OrderData.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="orderListByUsingGET"></a>
# **orderListByUsingGET**
> List&lt;OrderChangeData&gt; orderListByUsingGET(fromDateTime, toDateTime)

Get Order change data by date time period

Search the orders created or with the changed status within the date time interval.

### Example
```java
// Import classes:
//import com.am.rest.client.orders.ApiClient;
//import com.am.rest.client.orders.ApiException;
//import com.am.rest.client.orders.Configuration;
//import com.am.rest.client.orders.auth.*;
//import com.am.rest.client.orders.api.OrdersResourceApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


OrdersResourceApi apiInstance = new OrdersResourceApi();
OffsetDateTime fromDateTime = new OffsetDateTime(); // OffsetDateTime | RFC3339 date-time
OffsetDateTime toDateTime = new OffsetDateTime(); // OffsetDateTime | RFC3339 date-time
try {
    List<OrderChangeData> result = apiInstance.orderListByUsingGET(fromDateTime, toDateTime);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OrdersResourceApi#orderListByUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fromDateTime** | **OffsetDateTime**| RFC3339 date-time | [optional]
 **toDateTime** | **OffsetDateTime**| RFC3339 date-time | [optional]

### Return type

[**List&lt;OrderChangeData&gt;**](OrderChangeData.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="setOrderFailedUsingPUT"></a>
# **setOrderFailedUsingPUT**
> setOrderFailedUsingPUT(orderId, failureReason)

Set Order as Failed

ToYou API does not support the order cancellation. If 3rd party system is unable to create the retrieved order it should set it as Failed. Failed orders appear on Merchant Portal/Failed orders page in ToYou system

### Example
```java
// Import classes:
//import com.am.rest.client.orders.ApiClient;
//import com.am.rest.client.orders.ApiException;
//import com.am.rest.client.orders.Configuration;
//import com.am.rest.client.orders.auth.*;
//import com.am.rest.client.orders.api.OrdersResourceApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


OrdersResourceApi apiInstance = new OrdersResourceApi();
UUID orderId = new UUID(); // UUID | Order ID
String failureReason = "failureReason_example"; // String | 
try {
    apiInstance.setOrderFailedUsingPUT(orderId, failureReason);
} catch (ApiException e) {
    System.err.println("Exception when calling OrdersResourceApi#setOrderFailedUsingPUT");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderId** | [**UUID**](.md)| Order ID |
 **failureReason** | **String**|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

