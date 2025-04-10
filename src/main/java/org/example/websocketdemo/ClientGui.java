package org.example.websocketdemo;

import org.example.websocketdemo.client.MyStompClient;
import java.util.concurrent.ExecutionException;

public class ClientGui {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyStompClient myStompClient = new MyStompClient("TapTap"); // ✅ Semicolon here!

        myStompClient.sendMessage(new Message(" Rakamakafon!", "TapTap")); // ✅ Full correct Message
    }
}
