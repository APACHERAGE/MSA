package org.example.websocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/message")
    public void handleMessage(Message message) {
        System.out.println("Received Message from User: " + message.getUser() + ", Message: " + message.getMessage());
        messagingTemplate.convertAndSend("/topic/messages", message);
        System.out.println("Sent Message to topic/messages " + ", Message: " + message.getMessage() + "from User: " + message.getUser());
    }

}
