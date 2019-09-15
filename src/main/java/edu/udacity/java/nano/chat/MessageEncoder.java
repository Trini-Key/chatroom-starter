package edu.udacity.java.nano.chat;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
        System.out.println("MessageEncoder - init method called");
    }

    @Override
    public void destroy() {
        // Close resources
        System.out.println("MessageEncoder - destroy method called");
    }
}
