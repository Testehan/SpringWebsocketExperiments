package com.testehan.websocket.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	1.If you start the app go to the link from below...open it in 2 browsers..
		http://localhost:8080/
	2. Log in with one user in each browser...
	3. you can send notifications to every connected user...
	4. you can also send private notifications, by specifying the username of the designated notification

*/

@SpringBootApplication
public class Chpt5PushNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chpt5PushNotificationApplication.class, args);
	}

}
