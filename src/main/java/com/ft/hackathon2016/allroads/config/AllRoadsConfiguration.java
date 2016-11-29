package com.ft.hackathon2016.allroads.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;


public class AllRoadsConfiguration extends Configuration {

  private String suggestorHost;
  private int suggestorPort;
  private String contentApiUrl;
  private String contentApiAuthKey;
  private boolean mockSuggestorApi;

  public AllRoadsConfiguration(
          @JsonProperty("suggestorHost") String suggestorHost,
          @JsonProperty("suggestorPort") int suggestorPort,
          @JsonProperty("contentApiUrl") String contentApiUrl,
          @JsonProperty("contentApiAuthKey") String contentApiAuthKey,
          @JsonProperty("mockSuggestorApi") boolean mockSuggestorApi
  ) {
    super();
    this.suggestorHost = suggestorHost;
    this.suggestorPort = suggestorPort;
    this.contentApiUrl = contentApiUrl;
    this.contentApiAuthKey = contentApiAuthKey;
    this.mockSuggestorApi = mockSuggestorApi;
  }

  public String getSuggestorHost() {
    return suggestorHost;
  }
  
  public int getSuggestorPort() {
    return suggestorPort;
  }

  public String getContentApiUrl() {
    return contentApiUrl;
  }

  public String getContentApiAuthKey() {
    return contentApiAuthKey;
  }

  public boolean isMockSuggestorApi() {
    return mockSuggestorApi;
  }
}
