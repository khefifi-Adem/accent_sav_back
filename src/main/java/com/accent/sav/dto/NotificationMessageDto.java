package com.accent.sav.dto;

public class NotificationMessageDto {
    private String receipientToken;
    private String title;
    private String body;

    public NotificationMessageDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NotificationMessageDto(String receipientToken, String title, String body) {
        this.receipientToken = receipientToken;
        this.title = title;
        this.body = body;
    }

    public String getReceipientToken() {
        return receipientToken;
    }

    public void setReceipientToken(String receipientToken) {
        this.receipientToken = receipientToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
