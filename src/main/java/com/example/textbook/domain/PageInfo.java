package com.example.textbook.domain;

//用于显示页面提示信息
public class PageInfo {

    private String type;

    private String message;

    public PageInfo(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
