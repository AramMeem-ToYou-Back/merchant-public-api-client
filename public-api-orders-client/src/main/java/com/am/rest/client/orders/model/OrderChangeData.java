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

package com.am.rest.client.orders.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import org.threeten.bp.OffsetDateTime;
/**
 * OrderChangeData
 */


public class OrderChangeData {
  @JsonProperty("orderId")
  private UUID orderId = null;

  @JsonProperty("deliveryStatus")
  private String deliveryStatus = null;

  @JsonProperty("dateUpdated")
  private OffsetDateTime dateUpdated = null;

  public OrderChangeData orderId(UUID orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Unique order ID in ToYou system
   * @return orderId
  **/
  @Schema(required = true, description = "Unique order ID in ToYou system")
  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
  }

  public OrderChangeData deliveryStatus(String deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
    return this;
  }

   /**
   * The status of order delivery flow in ToYou
   * @return deliveryStatus
  **/
  @Schema(example = "ON THE WAY TO PICK UP", required = true, description = "The status of order delivery flow in ToYou")
  public String getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(String deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public OrderChangeData dateUpdated(OffsetDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
    return this;
  }

   /**
   * The date of the status change
   * @return dateUpdated
  **/
  @Schema(example = "2021-06-30T08:23:49.692Z", required = true, description = "The date of the status change")
  public OffsetDateTime getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(OffsetDateTime dateUpdated) {
    this.dateUpdated = dateUpdated;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderChangeData orderChangeData = (OrderChangeData) o;
    return Objects.equals(this.orderId, orderChangeData.orderId) &&
        Objects.equals(this.deliveryStatus, orderChangeData.deliveryStatus) &&
        Objects.equals(this.dateUpdated, orderChangeData.dateUpdated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, deliveryStatus, dateUpdated);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderChangeData {\n");
    
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    deliveryStatus: ").append(toIndentedString(deliveryStatus)).append("\n");
    sb.append("    dateUpdated: ").append(toIndentedString(dateUpdated)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
