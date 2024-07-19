package com.testehan.websocket.chat2.config;

import com.testehan.websocket.chat2.handler.SocketConnectionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
// EnableWebSocket annotation with Configuration and this is mandatory for processing the WebSocket request
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry)
    {
        // For adding a Handler we give the Handler class we
        // created before with End point Also we are managing
        // the CORS policy for the handlers so that other
        // domains can also access the socket
        webSocketHandlerRegistry
                // Create an instance of the Handler file which we created and pass the endpoint of the socket as a String
                .addHandler(new SocketConnectionHandler(),"/hello")
                /*
                    If your socket is only going to be used in the same application then don’t call the
                    setAllowedOrigins method. And if any other domain is going to use this application then pass the
                    domain address as the parameters. “*” means that any domain can connect to the socket.
                */
                .setAllowedOrigins("*");
    }
}
