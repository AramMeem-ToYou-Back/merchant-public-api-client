# AuthTokensDto

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**authExpirationDate** | **Integer** | AuthToken Expiration time. Expressed in seconds that have elapsed since epoch. | 
**authExpirationPeriod** | **Integer** | Time left until AuthToken expires. Expressed in seconds. | 
**authToken** | **String** | A JSON Web token intended for user authorization needs. | 
**refreshExpirationDate** | **Integer** | RefreshToken Expiration time. Expressed in seconds that have elapsed since epoch. | 
**refreshExpirationPeriod** | **Integer** | Time left until RefreshToken expires. Expressed in seconds. | 
**refreshToken** | **String** | A JSON Web token intended for authorization token refreshing. | 
