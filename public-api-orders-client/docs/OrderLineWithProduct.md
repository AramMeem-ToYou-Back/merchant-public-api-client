# OrderLineWithProduct

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | Product name in selected language (en or ar) | 
**quantity** | [**BigDecimal**](BigDecimal.md) | Number of products ordered | 
**price** | [**BigDecimal**](BigDecimal.md) | Product&#x27;s price (tax exclusive) | 
**note** | **String** | Ordered item note from Customer | 
**productId** | [**UUID**](UUID.md) | Product’s unique ID in ToYou system | 
**productOptionsValues** | [**List&lt;OptionValueOrderedWithProduct&gt;**](OptionValueOrderedWithProduct.md) |  |  [optional]
