package com.testehan.websocket.location.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Location {
    private String sender;
    private String recipient;
    private double latitude;
    private double longitude;
}
