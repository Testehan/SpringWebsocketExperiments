package com.testehan.websocket.chat2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	In postman i created a collection called Websockets, where there are 2 requests, Chat2 - user 1 and Chat2 - user 2,
	that can be used to connect to this app, and to send messages..

	If you start the app go to the link from below..enter a username and start writing messages
		http://localhost:8080/index.html

*/

@SpringBootApplication
public class Chpt2AnotherChatRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt2AnotherChatRoomApplication.class, args);
	}

}
