package com.example.bizx.ics.chatbot.dto;

public class ChatResponse {

    private String type;
    private String reply;
    private Object data;

    public ChatResponse() {
    }

    public ChatResponse(String type, String reply, Object data) {
        this.type = type;
        this.reply = reply;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}