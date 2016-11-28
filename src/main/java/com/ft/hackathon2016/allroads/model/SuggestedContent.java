package com.ft.hackathon2016.allroads.model;

public class SuggestedContent {
  private String title;
  private String webUrl;
  private String publishedDate;
  private String byline;
  private String standfirst;

  public SuggestedContent(String title, String webUrl, String publishedDate, String byline, String standfirst) {
    this.title = title;
    this.webUrl = webUrl;
    this.publishedDate = publishedDate;
    this.byline = byline;
    this.standfirst = standfirst;
  }
  
  public String getTitle() {
    return title;
  }
  
  public String getWebUrl() {
    return webUrl;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public String getByline() {
    return byline;
  }

  public String getStandfirst() {
    return standfirst;
  }
}
