package com.testehan.websocket.chatroom.config;

import com.testehan.websocket.chatroom.model.ChatMessage;
import com.testehan.websocket.chatroom.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
/*
    When applied to a class, it automatically creates a static SLF4J logger instance named
    log, targeting the SLF4J logging facade
*/
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    // purpose of this method is to inform the other participants when a user disconnected from the chat
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        /*
            The StompHeaderAccessor class in Spring Framework builds upon the SimpMessageHeaderAccessor class
            and is specifically designed to work with message headers in the context of the STOMP
            (Simple Text Oriented Messaging Protocol)
        */

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);

        }
    }

}
