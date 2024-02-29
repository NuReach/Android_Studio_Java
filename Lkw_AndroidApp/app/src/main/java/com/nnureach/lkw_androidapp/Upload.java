package com.nnureach.lkw_androidapp;

public class Upload {
    private String title;
    private String body;
    private String imageUrl;

    private String userId;
    public Upload() {
        // Default constructor required for Firestore
    }

    public Upload(String title, String body, String imageUrl,String userId) {
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserId(){
        return userId;
    }

}
