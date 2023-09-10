package entity;

import java.util.ArrayList;
import java.util.List;

public class ChatRequest {
    private List<ChatMessage> messages;

    public ChatRequest() {
        this.messages = new ArrayList<>();
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}