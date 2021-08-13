/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.service.impl;

import com.am.api.orders.client.example.api.OrdersApiClient;
import com.am.api.orders.client.example.persistence.OrderDataRepository;
import com.am.api.orders.client.example.service.OrderService;
import com.am.rest.client.orders.ApiException;
import com.am.rest.client.orders.api.OrdersResourceApi;
import com.am.rest.client.orders.model.OrderChangeData;
import com.am.rest.client.orders.model.OrderData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrdersResourceApi ordersResourceApi;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final RetryTemplate retryTemplate;
    private final OrderDataRepository orderDataRepository;

    public OrderServiceImpl(OrdersApiClient ordersApiClient,
                            ApplicationEventPublisher applicationEventPublisher,
                            @Qualifier("apiCallRetryTemplate") RetryTemplate retryTemplate,
                            OrderDataRepository orderDataRepository) {
        this.ordersResourceApi = new OrdersResourceApi(ordersApiClient);
        this.applicationEventPublisher = applicationEventPublisher;
        this.retryTemplate = retryTemplate;
        this.orderDataRepository = orderDataRepository;
    }

    @Override
    public List<OrderChangeData> fetchCurrentPublicOrders() {
        try {
            return retryTemplate.execute(context -> ordersResourceApi.orderListByUsingGET(null, null));
        } catch (ApiException e) {
            log.error("Can't fetch order list from ToYou", e);
            // TODO handle error
            throw new RuntimeException(e);
        }

    }

    @Override
    public OrderData fetchPublicOrder(UUID toyouOrderId) {
        try {
            return retryTemplate.execute(context -> ordersResourceApi.orderByIdByUsingGET(toyouOrderId));
        } catch (ApiException e) {
            log.error("Can't fetch order from ToYou", e);
            // TODO handle error
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveOrUpdateOrder(OrderData order) {
        final boolean isExistingOrderUpdated = orderDataRepository.save(order);
        if (isExistingOrderUpdated) {
            log.debug("Updated order {}", order);
        } else {
            log.debug("New order {}", order);
        }
    }
}
