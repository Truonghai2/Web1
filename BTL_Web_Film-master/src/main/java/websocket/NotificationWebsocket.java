package websocket;

import java.io.IOException;
import java.util.Collections;

import java.util.HashSet;

import java.util.Set;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/notifications")
public class NotificationWebsocket {
    
    private static Set<Session> sessionsN = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void handleOpen(Session session, EndpointConfig config) {
        // Add new session to the set
        sessionsN.add(session);
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        JsonObject jsonMessage = JsonParser.parseString(message).getAsJsonObject();
        String content = jsonMessage.get("content").getAsString();
        String type = jsonMessage.get("type").getAsString();
        String userId = jsonMessage.get("userId").getAsString();
        String username = jsonMessage.has("username") ? jsonMessage.get("username").getAsString() : null;
        
        JsonObject response = new JsonObject();
        response.addProperty("username", username);
        response.addProperty("id", userId);
        response.addProperty("body", content);
        response.addProperty("type", type);
        
        // Broadcast the response to all connected sessions
        for (Session session : sessionsN) {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(response.toString());
            }
        }
    }


    @OnClose
    public void handleClose(Session session) {
        
        sessionsN.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
        t.printStackTrace();
    }
}
