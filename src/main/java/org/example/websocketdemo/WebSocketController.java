package org.example.websocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final WebSocketSessionManager sessionManager;
    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate,WebSocketSessionManager sessionManager){
        this.sessionManager = sessionManager;
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/message")
    public void handleMessage(Message message) {
        System.out.println("Received Message from User: " + message.getUser() + ", Message: " + message.getMessage());
        messagingTemplate.convertAndSend("/topic/messages", message);
        System.out.println("Sent Message to topic/messages " + ", Message: " + message.getMessage() + "from User: " + message.getUser());
    }
    @MessageMapping("/connect")
    public void connectUser(String username) {
        sessionManager.addUsername(username);
        sessionManager.broadcastActiveUsernames();
        System.out.println(username+" connected");
    }
    @MessageMapping("/disconnect")
    public void disconnectUser(String username) {
        sessionManager.removeUsername(username);
        sessionManager.broadcastActiveUsernames();
        System.out.println(username+" disconnected");
    }

}
