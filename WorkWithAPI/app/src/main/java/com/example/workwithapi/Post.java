package com.example.workwithapi;

import com.fasterxml.jackson.annotation.*;

public class Post {
    private long postID;
    private long id;
    private String name;
    private String email;
    private String body;

    @JsonProperty("postId")
    public long getPostID() { return postID; }
    @JsonProperty("postId")
    public void setPostID(long value) { this.postID = value; }

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("body")
    public String getBody() { return body; }
    @JsonProperty("body")
    public void setBody(String value) { this.body = value; }
}