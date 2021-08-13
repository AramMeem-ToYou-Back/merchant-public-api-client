/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.persistence.impl;

import com.am.api.orders.client.example.persistence.OrderDataRepository;
import com.am.rest.client.orders.model.OrderData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryOrderDataRepository implements OrderDataRepository {
    private final ConcurrentHashMap<UUID, OrderData> ordersById = new ConcurrentHashMap<>();

    @Override
    public boolean save(OrderData orderData) {
        return ordersById.put(orderData.getOrderId(), orderData) != null;
    }

    @Override
    public Optional<OrderData> getById(UUID orderId) {
        return Optional.ofNullable(ordersById.get(orderId));
    }

    @Override
    public List<OrderData> find() {
        return List.copyOf(ordersById.values());
    }
}
