package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListItem {
  private String id;
  private String apiUrl;
  
  public ListItem(@JsonProperty("id") String id, @JsonProperty("apiUrl") String apiUrl) {
    this.id = id;
    this.apiUrl = apiUrl;
  }
  
  public String getId() {
    return id;
  }
  
  public String getApiUrl() {
    return apiUrl;
  }
}
