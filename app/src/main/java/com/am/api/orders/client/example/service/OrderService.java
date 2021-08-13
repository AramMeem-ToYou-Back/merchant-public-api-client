/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.service;

import com.am.rest.client.orders.model.OrderChangeData;
import com.am.rest.client.orders.model.OrderData;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderChangeData> fetchCurrentPublicOrders();

    OrderData fetchPublicOrder(UUID toyouOrderId);

    void saveOrUpdateOrder(OrderData order);
}
