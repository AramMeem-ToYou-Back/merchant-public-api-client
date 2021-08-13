# Java client for ToYou Public API

This sample client to ToYou Public API aimed at helping merchants and third-party developers 
to better understand how the Public API works and how they should use it to integrate their systems with ToYou.

## Project structure

General project structure and flows described in [class](doc/rendered/class-structure.png),
[sequence](doc/rendered/initial-connection-sequence.png) and access token service [activity](doc/rendered/access-token-service-activity.png)
diagrams.

## How to build and run sample project

### Requirements
* Java 11+
* Maven 3.x

### Configuration

Configuration defined in [application.properties](app/src/main/resources/application.properties) file.
Any of configuration method described in [Spring Boot](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#features.external-config)
documentation applicable.

### How to run sample

Any method described in 
[Running Your Application](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#using.running-your-application)
will work.
For example:

`mvn spring-boot:run`
