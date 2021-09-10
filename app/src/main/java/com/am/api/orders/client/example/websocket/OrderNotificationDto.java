/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.websocket;

import lombok.Data;

@Data
public class OrderNotificationDto {
    private String id;
    private String merchantId;
    private String orderId;
    private String lastModifiedDate;
    private String deliveryStatus;
}
