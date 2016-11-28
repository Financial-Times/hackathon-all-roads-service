package com.ft.hackathon2016.allroads.model;

/**
 * Created by peter.clark on 28/11/2016.
 */
public class ContentRequest {

  private String content;
  private String url;

  public ContentRequest() {
  }

  public ContentRequest(String url, String content){
    this.url = url;
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public String getUrl() {
    return url;
  }
}
