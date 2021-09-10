# OrderData

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**orderId** | [**UUID**](UUID.md) | Unique order ID in ToYou system | 
**orderNumber** | **String** | Unique human readable order number | 
**lang** | **String** | “en” or “ar”. The language in which the order has been created | 
**currency** | **String** | order total currency. Always in SAR | 
**orderTotal** | [**OrderDataOrderTotal**](OrderDataOrderTotal.md) |  | 
**estimatedPickupDate** | [**OffsetDateTime**](OffsetDateTime.md) | Estimated Date and time when driver will arrive at pick up location | 
**creationDate** | [**OffsetDateTime**](OffsetDateTime.md) | Date and time of order creation in ToYou system | 
**paymentType** | **String** | Credit: payments in Cash&lt;br&gt;Card: payments by Credit Card | 
**deliveryStatus** | **String** | Order&#x27;s current delivery status | 
**pickupLocation** | [**OrderDataPickupLocation**](OrderDataPickupLocation.md) |  |  [optional]
**merchantPosID** | [**UUID**](UUID.md) | Merchant&#x27;s Point of Sale ID in ToYou system | 
**orderLines** | [**List&lt;OrderLineWithProduct&gt;**](OrderLineWithProduct.md) |  |  [optional]
**driver** | [**OrderDataDriver**](OrderDataDriver.md) |  |  [optional]
