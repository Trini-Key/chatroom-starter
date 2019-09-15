package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * WebSocket message model
 */

public class Message {
    // TODO: add message model.
    @JSONField(name = "msg")
    private String message;
    @JSONField(name = "username")
    private String name;
    private String type;
    private String onlineCount;

    public Message() {

    }

    public Message(String message, String name, String type, String onlineCount) {
        this.message = message;
        this.name = name;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public static String jsonToString(String type, String username, String message, int onlineCount) {
        return JSON.toJSONString(new Message(message, username, type, String.valueOf(onlineCount)));
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = String.valueOf(onlineCount);
    }
}

