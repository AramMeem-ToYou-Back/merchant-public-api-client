package com.am.rest.client.orders.api;

import com.am.rest.client.orders.ApiException;
import com.am.rest.client.orders.ApiClient;
import com.am.rest.client.orders.Configuration;
import com.am.rest.client.orders.Pair;

import javax.ws.rs.core.GenericType;

import org.threeten.bp.OffsetDateTime;
import com.am.rest.client.orders.model.OrderChangeData;
import com.am.rest.client.orders.model.OrderData;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersResourceApi {
  private ApiClient apiClient;

  public OrdersResourceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public OrdersResourceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get Order data by ID
   * Use Order ID from order change data to get Order details
   * @param orderId Order ID (required)
   * @return OrderData
   * @throws ApiException if fails to make API call
   */
  public OrderData orderByIdByUsingGET(UUID orderId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      throw new ApiException(400, "Missing the required parameter 'orderId' when calling orderByIdByUsingGET");
    }
    // create path and map variables
    String localVarPath = "/v1/orders/{order-id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "order-id" + "\\}", apiClient.escapeString(orderId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "bearerAuth" };

    GenericType<OrderData> localVarReturnType = new GenericType<OrderData>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Get Order change data by date time period
   * Search the orders created or with the changed status within the date time interval.
   * @param fromDateTime RFC3339 date-time (optional)
   * @param toDateTime RFC3339 date-time (optional)
   * @return List<OrderChangeData>
   * @throws ApiException if fails to make API call
   */
  public List<OrderChangeData> orderListByUsingGET(OffsetDateTime fromDateTime, OffsetDateTime toDateTime) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/v1/orders".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "from-date-time", fromDateTime));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "to-date-time", toDateTime));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "bearerAuth" };

    GenericType<List<OrderChangeData>> localVarReturnType = new GenericType<List<OrderChangeData>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Set Order as Failed
   * ToYou API does not support the order cancellation. If 3rd party system is unable to create the retrieved order it should set it as Failed. Failed orders appear on Merchant Portal/Failed orders page in ToYou system
   * @param orderId Order ID (required)
   * @param failureReason  (required)
   * @throws ApiException if fails to make API call
   */
  public void setOrderFailedUsingPUT(UUID orderId, String failureReason) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      throw new ApiException(400, "Missing the required parameter 'orderId' when calling setOrderFailedUsingPUT");
    }
    // verify the required parameter 'failureReason' is set
    if (failureReason == null) {
      throw new ApiException(400, "Missing the required parameter 'failureReason' when calling setOrderFailedUsingPUT");
    }
    // create path and map variables
    String localVarPath = "/v1/orders/{order-id}/failed".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "order-id" + "\\}", apiClient.escapeString(orderId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "failure-reason", failureReason));

    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "bearerAuth" };

    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
