package com.testehan.websocket.notification.controller;

import com.testehan.websocket.notification.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class SpecificUserNotificationController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Message message, Authentication authentication) {
        log.info(authentication.getName() + " sent a message to " + message.getTo());
        /*
        What the messaging template does is, it sends the message to a destination which starts
        with /user , then appends it with the destination we provided in the function, i.e /queue
        and then attaches the user session id of the user we specified in the convertAndSendToUser
        function call.

        So basically, the convertAndSendToUser send the message to the destination
        /user/queue-<user-session-id> . This destination is created when a user logins and subscribes
        to the destination /user/specific.
        When the user is logged in and subscribes to the destination /user/specific , it sends the
        valid logged-in session id along with it. Spring then automatically handles that
        subscribing to /user/queue will automatically subscribe to the specific destination for the
        logged-in user. i.e /user/queue-<user-session-id>

         */
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/queue", message);
    }
}
