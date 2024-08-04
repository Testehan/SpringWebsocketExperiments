package com.testehan.websocket.hello.config;

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
        // enable a simple memory-based message broker to carry the greeting messages back to the
        // client on destinations prefixed with /topic
        config.enableSimpleBroker("/topic");

        // It also designates the /app prefix for messages that are bound for methods annotated
        // with @MessageMapping. This prefix will be used to define all the message mappings. For
        // example, /app/hello is the endpoint that the GreetingController.greeting() method is mapped to handle
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello-websocket");
    }

}
