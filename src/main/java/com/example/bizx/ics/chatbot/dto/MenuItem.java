package com.example.bizx.ics.chatbot.dto;

public class MenuItem {

    private String label;
    private String path;

    public MenuItem() {
    }

    public MenuItem(String label, String path) {
        this.label = label;
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}