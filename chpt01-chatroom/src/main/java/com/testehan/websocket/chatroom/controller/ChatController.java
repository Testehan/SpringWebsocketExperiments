package com.testehan.websocket.chatroom.controller;

import com.testehan.websocket.chatroom.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    // @MessageMapping configures the URL/endpoint that we want to use to invoke this sendMessage method
    @MessageMapping("/chat.sendMessage")
    // @SendTo configures to which topic or queue we want to send the message; "/topic" is configured in
    // WebSocketConfig method configureMessageBroker
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    /*
        SimpMessageHeaderAccessor class in Spring Framework is used for working with message headers in
        protocols that support basic messaging patterns, specifically those related to Simple Messaging
        (often referred to as "SimpMessaging")

        Here's a breakdown of its key functionalities:
            Accessing Message Headers: It provides methods to access and modify the headers associated with a
            message. These headers contain information about the message
            Uniform Header Access: It offers a consistent way to access headers across different Simple
            Messaging protocols. Spring supports various messaging protocols like STOMP (Simple Text Oriented
            Messaging Protocol) and WebSocket messaging, and SimpMessageHeaderAccessor provides a unified
            approach for working with headers regardless of the underlying protocol
    */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
