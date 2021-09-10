/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.persistence;

import com.am.rest.client.orders.model.OrderData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDataRepository {
    boolean save(OrderData orderData);

    Optional<OrderData> getById(UUID orderId);

    List<OrderData> find();

}
