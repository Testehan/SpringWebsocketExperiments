package com.testehan.websocket.notification.controller;

import com.testehan.websocket.notification.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class AllUsersNotificationController {

    @MessageMapping("/application")
    @SendTo("/topic/messages")
    public Message send(final Message message) throws Exception {
        return message;
    }
}
