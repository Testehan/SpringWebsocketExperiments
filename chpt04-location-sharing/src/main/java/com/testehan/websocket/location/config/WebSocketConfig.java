package com.testehan.websocket.location.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/app");
        registry.setApplicationDestinationPrefixes("/geoplus");

        /*
            a Spring WebSocket configuration sets the prefix for messages targeted at specific users.
            In a WebSocket application, there might be scenarios where you want to send messages
            directly to a particular user. For example, sending a private message or user-specific
            notifications. This configuration is often used in conjunction with user authentication and
            authorization mechanisms. It ensures that messages are delivered only to the intended user

             This configuration separates user-specific destinations from general destinations.
             While /topic might be used for broadcasting messages to all interested clients, /user is
             specifically used for messages directed at individual users.
        */
        registry.setUserDestinationPrefix("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /*

         This configuration ensures that WebSocket messages in the application are converted to and
         from JSON using Jackson’s ObjectMapper. By returnig false, to indicate that the default
         message converters should not be modified. This means that the method is providing additional
         configuration without completely replacing the default converters.

    */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

}
