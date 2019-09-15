package edu.udacity.java.nano.chat;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        System.out.println(msg);

        for (Map.Entry<String, Session> entry : onlineSessions.entrySet()) {
            try {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                entry.getValue().getBasicRemote().sendObject(msg);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        //TODO: add on open connection.
        //Every time a new session is created add it to HashMap onlineSessions
        onlineSessions.put(session.getId(), session);
        Message message = new Message();
        message.setName(session.getId());
        message.setMessage(message.getName() + " connected!");
        sendMessageToAll(message.getMessage());
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws IOException, EncodeException {
        //TODO: add send message.
        //Create a new Message object from jsonStr
        //sendMessageToAll(message);

        Message message = new Message(jsonStr);
        message.setName(session.getPathParameters().get("username"));
        sendMessageToAll(message.getMessage());
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        //TODO: add close connection.
        //Every time a session is closed remove it from HashMap onlineSessions
        System.out.println(session.getBasicRemote().getSendStream().toString());
        session.close();
        sendMessageToAll(session.getId() + " Disconnected");
        onlineSessions.remove(session.getId(), session);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
