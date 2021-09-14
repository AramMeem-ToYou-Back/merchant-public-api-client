/*
 * Copyright © 2021 Aram Meem Company Limited. All Rights Reserved.
 */

package com.am.api.orders.client.example.websocket;

import com.am.api.orders.client.example.authentication.AccessTokenService;
import com.am.api.orders.client.example.event.NewOrderReceivedEvent;
import com.am.api.orders.client.example.event.WsListenerConnectedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Profile("!test")
@Component
@Slf4j
public class PublicOrderNotificationsClient {
    private static final AtomicBoolean shutdownRequested = new AtomicBoolean(false);
    private  StompSession session;
    private final AccessTokenService accessTokenService;
    private final String merchantId;
    private final String basePath;
    private final ApplicationEventPublisher applicationEventPublisher;

    public PublicOrderNotificationsClient(AccessTokenService accessTokenService,
                                          @Value("${merchant.id}") String merchantId,
                                          @Value("${toyou.api.protocol}") String protocol,
                                          @Value("${toyou.api.host}") String host,
                                          @Value("${toyou.api.port}") int port,
                                          ApplicationEventPublisher applicationEventPublisher) {
        this.accessTokenService = accessTokenService;
        this.merchantId = merchantId;
        this.applicationEventPublisher = applicationEventPublisher;
        basePath = String.format("%s://%s:%d/publicwebsocket/ws?access_token=",
                "https".equals(protocol) ? "wss" : "ws", host, port);
    }

    @PostConstruct
    public void init() {
        runWsClient();
    }

    @PreDestroy
    public void shutdown() {
        shutdownRequested.set(true);
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    public void runWsClient() {
        String bearer = "Bearer " + accessTokenService.getAccessToken();
        String url = basePath + URLEncoder.encode(bearer, StandardCharsets.UTF_8);

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(createHeartbeatsTaskScheduler());
        stompClient.setDefaultHeartbeat(new long[]{10_000L, 10_000L});

        StompSessionHandler sessionHandler = new StompMerchantNotificationsSessionHandler(merchantId, applicationEventPublisher);
        stompClient.connect(url, sessionHandler);
    }

    private TaskScheduler createHeartbeatsTaskScheduler() {
        ThreadPoolTaskScheduler heartbeatTaskScheduler = new ThreadPoolTaskScheduler();
        heartbeatTaskScheduler.setPoolSize(1);
        heartbeatTaskScheduler.setDaemon(true);
        heartbeatTaskScheduler.setThreadNamePrefix("wss-heartbeat-thread-");
        heartbeatTaskScheduler.initialize();
        return heartbeatTaskScheduler;
    }

    private class StompMerchantNotificationsSessionHandler extends StompSessionHandlerAdapter {
        private final String merchantId;
        private final ApplicationEventPublisher applicationEventPublisher;

        private StompMerchantNotificationsSessionHandler(String merchantId, ApplicationEventPublisher applicationEventPublisher) {
            this.merchantId = merchantId;
            this.applicationEventPublisher = applicationEventPublisher;
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return OrderNotificationDto.class;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            session.subscribe("notifications-" + merchantId, this);
            log.info("Listener connected");
            applicationEventPublisher.publishEvent(new WsListenerConnectedEvent());
            PublicOrderNotificationsClient.this.session = session;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            log.debug("Received : " + payload + " with headers : " + headers);
            if (payload != null) {
                if (payload instanceof OrderNotificationDto) {
                    OrderNotificationDto orderNotificationDto = (OrderNotificationDto) payload;
                    applicationEventPublisher.publishEvent(new NewOrderReceivedEvent(
                            UUID.fromString(orderNotificationDto.getOrderId())
                    ));
                } else {
                    throw new RuntimeException("Unexpected input type: " + payload.getClass());
                }
            }
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            log.error("ERROR: command={}, headers={}", command, headers, exception);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            log.error("Transport ERROR", exception);
            if (!shutdownRequested.get()) {
                log.warn("Re-connect request");
                runWsClient();
            }
        }
    }
}
