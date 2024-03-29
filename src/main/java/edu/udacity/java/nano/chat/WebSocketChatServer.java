package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
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
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message msg) {
        for (Session s : onlineSessions.values()){
            try{
                Log keyLog = new Log("log.txt");

                keyLog.keysLogger.setLevel(Level.INFO);

                keyLog.keysLogger.info(JSON.toJSONString(msg));

                s.getBasicRemote().sendText(JSON.toJSONString(msg));
            }catch(IOException | SecurityException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //Every time a new session is created add it to HashMap onlineSessions
        onlineSessions.put(session.getId(), session);
        Message newUser = new Message();
        newUser.setMessage("Connected!");
        newUser.setType("ENTER");
        newUser.setOnlineCount(onlineSessions.size());
        sendMessageToAll(newUser);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //Create a new Message object from jsonStr
        //sendMessageToAll(message);
        //by default update the onlinecount for each message
        //If message type = speak do something if not just update online count
       Message userMsg = JSON.parseObject(jsonStr, Message.class);
        userMsg.setOnlineCount(onlineSessions.size());
        if(userMsg.getMessage() != null){
            userMsg.setType("SPEAK");
        }
        sendMessageToAll(userMsg);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //Every time a session is closed remove it from HashMap onlineSessions
        onlineSessions.remove(session.getId());
        Message signOff = new Message();
        signOff.setOnlineCount(onlineSessions.size());
        signOff.setMessage("Disconnected!");
        signOff.setType("LEAVE");
        sendMessageToAll(signOff);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
