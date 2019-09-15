package edu.udacity.java.nano.chat;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * WebSocket message model
 */

public class Message {
    // TODO: add message model.
    private String message;
    private String name;
    private String type;
    private String onlineCount;

    public Message() {

    }

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, String name, String type) {
        this.message = message;
        this.name = name;
        this.type = type;
    }

    public Message(String message, String name, String type, String onlineCount) {
        this.message = message;
        this.name = name;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public Message(String name, String message) {
        this.message = message;
        this.name = name;
    }

    @Override
    public String toString(){
        return super.toString();
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

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

}

