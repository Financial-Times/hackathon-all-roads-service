package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentItemIdentifier {

    private final String apiUrl;

    public ContentItemIdentifier(@JsonProperty("id") final String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getEnrichedContentApiUrl() {
        return apiUrl.replaceAll("content","enrichedcontent");
    }

}
