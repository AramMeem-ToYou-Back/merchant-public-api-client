/*
 * ToYou's public API:  Orders
 * Allows to get notified about new orders, order status changes and retrieve order details.<br/> As soon as a driver has been assigned to the order, it becomes available to retrieve by 3rd party. It means that driver is already on the way to pickup the order. The status of the order will change during the delivery. ToYou provides the information on each status change of the delivery
 *
 * OpenAPI spec version: 1-oas3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.am.rest.client.orders.auth;

public enum OAuthFlow {
    accessCode, implicit, password, application
}
