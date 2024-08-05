package com.testehan.websocket.location.controller;

import com.testehan.websocket.location.model.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
class LocationController {
    private final SimpMessagingTemplate wsTemplate;

    /*
        We created websocket connection to send the location to /geoplus/location endpoint and receive
        the location updates to relevant userid subscription /app/{userId}/user-locations
    */
    @MessageMapping("/location")
    public void sendLocation(@Payload Location location) {
        log.info("Received location : {}", location);
//        var recipient = location.getRecipient();
//        var sender = location.getSender();
//        location.setRecipient(sender);
//        location.setSender(recipient);
        wsTemplate.convertAndSendToUser(location.getRecipient(), "/user-locations", location);
        wsTemplate.convertAndSendToUser(location.getSender(), "/user-locations", location);
    }
}
