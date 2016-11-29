package com.ft.hackathon2016.allroads.config;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;


public class AllRoadsConfiguration extends Configuration {

  private String suggestorHost;
  private int suggestorPort;
  private String contentApiUrl;
  private String contentApiAuthKey;
  private boolean mockSuggestorApi;
  private Set<UUID> topStoriesUuids;

  public AllRoadsConfiguration(
          @JsonProperty("suggestorHost") String suggestorHost,
          @JsonProperty("suggestorPort") int suggestorPort,
          @JsonProperty("contentApiUrl") String contentApiUrl,
          @JsonProperty("contentApiAuthKey") String contentApiAuthKey,
          @JsonProperty("mockSuggestorApi") boolean mockSuggestorApi,
          @JsonProperty("topStoriesLists") Set<UUID> topStoriesUuids
  ) {
    super();
    this.suggestorHost = suggestorHost;
    this.suggestorPort = suggestorPort;
    this.contentApiUrl = contentApiUrl;
    this.contentApiAuthKey = contentApiAuthKey;
    this.mockSuggestorApi = mockSuggestorApi;
    this.topStoriesUuids = topStoriesUuids;
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
  
  public Set<UUID> getTopStoriesUUIDs() {
    return topStoriesUuids;
  }
}
