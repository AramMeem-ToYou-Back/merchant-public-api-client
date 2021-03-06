# client-catalog-api

Api Documentation
- API version: 1.0

Api Documentation


*Automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)*


## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.am.rest.client</groupId>
  <artifactId>client-catalog-api</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.am.rest.client:client-catalog-api:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/client-catalog-api-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java
import com.am.rest.client.catalog.*;
import com.am.rest.client.catalog.auth.*;
import com.am.rest.client.catalog.model.*;
import com.am.rest.client.catalog.api.MerchantUserResourceApi;

import java.io.File;
import java.util.*;

public class MerchantUserResourceApiExample {

    public static void main(String[] args) {
        
        MerchantUserResourceApi apiInstance = new MerchantUserResourceApi();
        String authorization = "authorization_example"; // String | Refresh token
        try {
            AuthTokensDto result = apiInstance.getTokensUsingGET(authorization);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MerchantUserResourceApi#getTokensUsingGET");
            e.printStackTrace();
        }
    }
}
import com.am.rest.client.catalog.*;
import com.am.rest.client.catalog.auth.*;
import com.am.rest.client.catalog.model.*;
import com.am.rest.client.catalog.api.MerchantUserResourceApi;

import java.io.File;
import java.util.*;

public class MerchantUserResourceApiExample {

    public static void main(String[] args) {
        
        MerchantUserResourceApi apiInstance = new MerchantUserResourceApi();
        LoginData body = new LoginData(); // LoginData | Login data object
        try {
            AuthTokensDto result = apiInstance.getTokensUsingPOST(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MerchantUserResourceApi#getTokensUsingPOST");
            e.printStackTrace();
        }
    }
}
```

## Documentation for API Endpoints

All URIs are relative to *//https://sand.toyou.delivery/*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*MerchantUserResourceApi* | [**getTokensUsingGET**](docs/MerchantUserResourceApi.md#getTokensUsingGET) | **GET** /catalog/v1/merchantuser/authtoken | Get refreshed tokens
*MerchantUserResourceApi* | [**getTokensUsingPOST**](docs/MerchantUserResourceApi.md#getTokensUsingPOST) | **POST** /catalog/v1/merchantuser/authtoken | Generate tokens for existing merchant user by email and password

## Documentation for Models

 - [AuthTokensDto](docs/AuthTokensDto.md)
 - [LoginData](docs/LoginData.md)

## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


