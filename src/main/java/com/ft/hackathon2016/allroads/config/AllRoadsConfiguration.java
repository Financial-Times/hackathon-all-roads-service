package com.ft.hackathon2016.allroads.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;


public class AllRoadsConfiguration extends Configuration {

  private String suggestorHost;
  private int suggestorPort;
  private String contentApiUrl;
  private String contentApiAuthKey;

  public AllRoadsConfiguration(
          @JsonProperty("suggestorHost") String suggestorHost,
          @JsonProperty("suggestorPort") int suggestorPort,
          @JsonProperty("contentApiUrl") String contentApiUrl,
          @JsonProperty("contentApiAuthKey") String contentApiAuthKey
  ) {
    super();
    this.suggestorHost = suggestorHost;
    this.suggestorPort = suggestorPort;
    this.contentApiUrl = contentApiUrl;
    this.contentApiAuthKey = contentApiAuthKey;
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
}
