/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.event.listener;

import com.am.api.orders.client.example.event.NewOrderReceivedEvent;
import com.am.api.orders.client.example.event.WsListenerConnectedEvent;
import com.am.api.orders.client.example.service.OrderService;
import com.am.rest.client.orders.model.OrderChangeData;
import com.am.rest.client.orders.model.OrderData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class OrderEventListener {
    private final OrderService orderService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ExecutorService executorService;

    public OrderEventListener(OrderService orderService, ApplicationEventPublisher applicationEventPublisher) {
        this.orderService = orderService;
        this.applicationEventPublisher = applicationEventPublisher;
        executorService = Executors.newSingleThreadExecutor();
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdownNow();
    }

    @EventListener
//    @Async
    public void onConnection(WsListenerConnectedEvent event) {
        log.warn("onConnection!!!!");
        executorService.execute(this::fetchCurentOrders);
    }

    private void fetchCurentOrders() {
        try {
            final List<OrderChangeData> orderChangeData = orderService.fetchCurrentPublicOrders();
            log.debug("Current orders fetched onConnection: {}", orderChangeData.size());
            orderChangeData.stream()
                    .map(o -> new NewOrderReceivedEvent(o.getOrderId()))
                    .forEach(applicationEventPublisher::publishEvent);
        } catch (Exception e) {
            log.error("Unexpected error", e);
        }
    }

    @EventListener
    public void onNewOrder(NewOrderReceivedEvent event) {
//        log.debug("New order received: {}", event.getOrderId());
        final OrderData orderData = orderService.fetchPublicOrder(event.getOrderId());
        orderService.saveOrUpdateOrder(orderData);
    }
}
