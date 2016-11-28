package com.ft.hackathon2016.allroads.fetchers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentApiGetArticleResponse {

    private final String id;
    private final String title;
    private final String byline;
    private final String publishedDate;
    private final String webUrl;
    private final String standfirst;

    public ContentApiGetArticleResponse(
            @JsonProperty("id") final String id,
            @JsonProperty("title") final String title,
            @JsonProperty("byline") final String byline,
            @JsonProperty("publishedDate") final String publishedDate,
            @JsonProperty("webUrl") final String webUrl,
            @JsonProperty("standfirst") final String standfirst) {

        this.id = id;
        this.title = title;
        this.byline = byline;
        this.publishedDate = publishedDate;
        this.webUrl = webUrl;
        this.standfirst = standfirst;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getStandfirst() {
        return standfirst;
    }
}
