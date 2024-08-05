package com.testehan.websocket.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //  The /topic destination will be used to send notifications to all users and the
        //  /queue destination will be used to send to specific users.
        config.enableSimpleBroker("/topic","/queue");

        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //  One with SockJS enabled and the other just for WebSocket. This is done because not all
        //  browsers support WebSockets and when it's not available we can fall back to using SockJS.

        registry.addEndpoint("/ws");
        registry.addEndpoint("/ws").withSockJS();
    }
}
